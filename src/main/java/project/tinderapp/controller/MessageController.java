package project.tinderapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.tinderapp.dto.MessageDto;
import project.tinderapp.entity.Message;
import project.tinderapp.entity.User;
import project.tinderapp.service.MessageService;
import project.tinderapp.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/Messagecontroller")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageDto message) {
        User fromUser = userService.findById(message.getFromUserId());
        User toUser = userService.findById(message.getToUserId());

        if (fromUser == null || toUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Message newMessage = messageService.sendMessage(fromUser,toUser, message.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(newMessage);
    }

    @GetMapping("/between/{fromUserId}/{toUserId}")
    public ResponseEntity<List<Message>> getMessageBetween(@PathVariable("fromUserId") long fromUserId,
                                                           @PathVariable long toUserId) {
        User fromUser = userService.findById(fromUserId);
        User toUser = userService.findById(toUserId);

        if (fromUser == null || toUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Message> messages = messageService.getMessagesBetweenUsers(fromUser, toUser);
        if (messages.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(messages);
    }

}

