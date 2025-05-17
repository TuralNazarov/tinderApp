package project.tinderapp.service.impl;

import org.springframework.stereotype.Service;
import project.tinderapp.entity.User;
import project.tinderapp.repository.UserRepository;
import project.tinderapp.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

}
