package project.tinderapp.service;

import project.tinderapp.entity.Message;
import project.tinderapp.entity.User;

import java.util.List;

public interface MessageService {
    Message sendMessage(User sender, User receiver, String content);
    List<Message> getMessagesBetweenUsers(User user1, User user2);
}
