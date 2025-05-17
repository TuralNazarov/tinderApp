package project.tinderapp.service;

import project.tinderapp.entity.Like;
import project.tinderapp.entity.User;

import java.util.List;

public interface LikeService {
    Like likeUser(User fromUser, User toUser, boolean liked);

    List<Like> getLikedUsers(User fromUser);

    List<Like> getLikeBetween(User fromUser, User toUser);
}
