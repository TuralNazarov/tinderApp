package project.tinderapp.service;

import project.tinderapp.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);
    public List<User> findAll();
    public User findById(long id);
    public void delete(long id);
}