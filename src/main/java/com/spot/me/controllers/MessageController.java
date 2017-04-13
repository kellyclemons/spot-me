package com.spot.me.controllers;

import com.spot.me.Parsers.RootParser;
import com.spot.me.entities.Message;
import com.spot.me.entities.Profile;
import com.spot.me.entities.User;
import com.spot.me.modelViews.ProfileView;
import com.spot.me.serializers.MessageSerializer;
import com.spot.me.serializers.RootSerializer;
import com.spot.me.services.MessageRepository;
import com.spot.me.services.UserRepository;
import com.spot.me.utilities.Messenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class MessageController {

    @Autowired
    MessageRepository messages;

    @Autowired
    UserRepository users;

    RootSerializer rootSerializer;
    MessageSerializer messageSerializer;

    public MessageController() {
        rootSerializer = new RootSerializer();
        messageSerializer = new MessageSerializer();
    }

    @RequestMapping(path = "/messages", method= RequestMethod.POST)
    public Map<String, Object> SendMessage(HttpServletResponse response, @RequestBody RootParser<Messenger> parser) {
        Messenger message = parser.getData().getEntity();
        User author = users.findFirstById(message.getAuthorId());
        User receiver = users.findFirstById(message.getAuthorId());
        Message msg = new Message(message.getMessage(),author, receiver);
        messages.save(msg);

        return rootSerializer.serializeOne(
                "/register/" + message.getAuthorId(),
                msg,
                messageSerializer);
    }

    @RequestMapping(path = "/messages/{id}", method= RequestMethod.GET)
    public Map<String, Object> findAllMessages(@PathVariable("id") String id) {
        User u = users.findFirstById(id);
        List<Message> msgs = messages.findAllMessageByReceiverId(id);

        return rootSerializer.serializeMany(
                "/profile/" + u.getId(),
                msgs,
                messageSerializer);

    }

}
