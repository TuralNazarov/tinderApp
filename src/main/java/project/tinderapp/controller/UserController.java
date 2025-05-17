package project.tinderapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.tinderapp.entity.Message;
import project.tinderapp.entity.User;
import project.tinderapp.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUsers() {
        Message message = new Message();
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        Message message = new Message();
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping("/{id}/")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        Message message = new Message();
        return ResponseEntity.ok(userService.findById(id));
    }
}
