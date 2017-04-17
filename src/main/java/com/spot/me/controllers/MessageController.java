package com.spot.me.controllers;

import com.spot.me.parsers.RootParser;
import com.spot.me.entities.Message;
import com.spot.me.entities.User;
import com.spot.me.serializers.MessageSerializer;
import com.spot.me.serializers.RootSerializer;
import com.spot.me.services.MessageRepository;
import com.spot.me.services.UserRepository;
import com.spot.me.utilities.Messenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User author = users.findFirstByEmail(u.getName());
        User receiver = users.findFirstById(message.getReceiverId());
        Message msg = new Message(message.getMessage(),author, receiver);
        messages.save(msg);

        return rootSerializer.serializeOne(
                "/messages/" + message.getAuthorId(),
                msg,
                messageSerializer);
    }

    @RequestMapping(path = "/users/{userid}/messages/{messageid}", method= RequestMethod.GET)
    public Map<String, Object> findOneMessage(@PathVariable("userid") String userId, @PathVariable("messageid") String messageId) {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByEmail(u.getName());

        Message msg = messages.findOne(messageId);

        return rootSerializer.serializeOne(
                "/messages/" + user.getId(),
                msg,
                messageSerializer);
    }

    @RequestMapping(path="/users/{userid}/messages", method=RequestMethod.GET)
    public Map<String, Object> findAllMessagesForUser(@PathVariable("userid") String userId){
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByEmail(u.getName());
        List<Message> msgs = messages.findAllMessageByReceiverId(user.getId());

        return rootSerializer.serializeMany(
                "/messages/" + user.getId(),
                msgs,
                messageSerializer);
    }

    @RequestMapping(path = "/users/{userid}/messages/{messageid}", method= RequestMethod.DELETE)
    public Map<String, Object> DeleteOneMessage(@PathVariable("userid") String userId, @PathVariable("messageid") String messageId) {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByEmail(u.getName());

        Message msg = messages.findOne(messageId);
        messages.delete(msg.getId());

        return rootSerializer.serializeOne(
                "/messages/" + user.getId(),
                msg,
                messageSerializer);
    }

    @RequestMapping(path="/users/{userid}/messages", method=RequestMethod.DELETE)
    public Map<String, Object> DeleteAllMessagesForUser(@PathVariable("userid") String userId){
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByEmail(u.getName());
        List<Message> msgs = messages.findAllMessageByReceiverId(user.getId());

        for (Message m : msgs) {
            messages.delete(m.getId());
        }

        return rootSerializer.serializeMany(
                "/messages/" + user.getId(),
                msgs,
                messageSerializer);
    }
}
