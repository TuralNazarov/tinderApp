package project.tinderapp.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.tinderapp.entity.Like;
import project.tinderapp.entity.User;
import project.tinderapp.service.LikeService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<Like> likeuser(@RequestBody Like like) {
        Like like1 =likeService.likeUser(like.getFromUser(),like.getToUser(),like.isLiked());
        return ResponseEntity.ok(like1);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Like>> getAllLikes(@PathVariable Long userId) {
        User fromUser = new User();
        User toUser = new User();
        fromUser.setId(userId);
        toUser.setId(userId);
        List<Like> likes =likeService.getLikeBetween(fromUser,toUser);
        return ResponseEntity.ok(likes);
    }

    @GetMapping("/between")
    public ResponseEntity<List<Like>> getLikeBetween(
            @RequestParam Long fromUserId,
            @RequestParam Long toUserId) {

        User fromUser = new User();
        fromUser.setId(fromUserId);

        User toUser = new User();
        toUser.setId(toUserId);

        List<Like> likes = likeService.getLikeBetween(fromUser, toUser);
        return ResponseEntity.ok(likes);
    }
}
