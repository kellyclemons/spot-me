package com.spot.me.controllers;
import java.util.Map;

import com.spot.me.entities.ProfilePicture;
import com.spot.me.entities.User;
import com.spot.me.parsers.RootParser;
import com.spot.me.serializers.ProfilePictureSerializer;
import com.spot.me.serializers.RootSerializer;
import com.spot.me.services.ProfilePictureRepository;
import com.spot.me.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;


@RestController
@CrossOrigin(origins = "*")
public class UploadController {
    ProfilePictureSerializer photoSerializer;
    RootSerializer rootSerializer;

    @Autowired
    ProfilePictureRepository photos;

    @Autowired
    UserRepository users;

    @Value("${cloud.aws.s3.bucket}")
    String bucket;

    @Autowired
    AmazonS3Client s3;

    public UploadController() {
        photoSerializer = new ProfilePictureSerializer();
        rootSerializer = new RootSerializer();
    }

    @RequestMapping(path = "/profile-picture", method = RequestMethod.GET)
    public Map<String, Object> findAllPost() {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByEmail(u.getName());
        ProfilePicture result = photos.findFirstByUserId(user.getId());

        return rootSerializer.serializeOne("/profile-picture", result, photoSerializer);
    }

    @RequestMapping(path = "/profile-picture", method = RequestMethod.POST)
    public Map<String, Object> storePost(@RequestBody RootParser<ProfilePicture> parser) {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByEmail(u.getName());
        ProfilePicture photo = parser.getData().getEntity();
        ProfilePicture pic = new ProfilePicture(photo.getPhotoUrl(), user);
        photos.save(pic);

        return rootSerializer.serializeOne(
                "/photo-posts/" + photo.getId(),
                photo,
                photoSerializer);
    }

    @RequestMapping(path = "/photo-posts/upload", method = RequestMethod.POST)
    public Map<String, Object> uploadPost(@RequestParam("photo") MultipartFile file)
            throws Exception {
        // Creating a new PhotoPost Entity
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByEmail(u.getName());
        ProfilePicture photo = new ProfilePicture();

        photo
                .setPhotoUrl("https://s3.amazonaws.com/" + bucket + "/" + file.getOriginalFilename());

        // Setup S3 request with bucket name, filename, file contents, and empty meta data
        PutObjectRequest s3Req = new PutObjectRequest(
                bucket,
                file.getOriginalFilename(),
                file.getInputStream(),
                new ObjectMetadata());

        // Save the object to s3
        s3.putObject(s3Req);
        photo.setUser(user);

        photos.save(photo);

        return rootSerializer.serializeOne(
                "/photo-posts/" + photo.getId(),
                photo,
                photoSerializer);
    }
}
