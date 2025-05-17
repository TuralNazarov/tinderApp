package project.tinderapp.service.impl;

import org.springframework.stereotype.Service;
import project.tinderapp.entity.Message;
import project.tinderapp.entity.User;
import project.tinderapp.repository.MessageRepository;
import project.tinderapp.service.MessageService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageSericeImpl implements MessageService {

    MessageRepository messageRepository;

    @Override
    public Message sendMessage(User sender, User receiver, String content) {
        Message newMessage = new Message();
        newMessage.setSender(sender);
        newMessage.setReceiver(receiver);
        newMessage.setText(content);
        newMessage.setTimestamp(LocalDateTime.now());

        return messageRepository.save(newMessage);
    }

    @Override
    public List<Message> getMessagesBetweenUsers(User user1, User user2) {
        return messageRepository.findAllBySenderAndReceiverOrReceiverAndSenderOrderByTimestamp(user1,user1,user2,user2);
    }
}
