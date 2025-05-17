package project.tinderapp.service.impl;

import org.springframework.stereotype.Service;
import project.tinderapp.entity.Like;
import project.tinderapp.entity.User;
import project.tinderapp.repository.LikeRepository;
import project.tinderapp.service.LikeService;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    private LikeRepository likeRepository;

    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public Like likeUser(User fromUser, User toUser, boolean liked) {

        List<Like> isLiked= likeRepository.findByFromUserAndToUser(fromUser, toUser);
        Like like;
        if(isLiked.isEmpty()){
            like = new Like();
            like.setFromUser(fromUser);
            like.setToUser(toUser);
            like.setLiked(liked);
            likeRepository.save(like);
        }
        else{
            like = isLiked.get(0);
            like.setLiked(liked);
            return likeRepository.save(like);
        }
        return likeRepository.save(like);
    }

    @Override
    public List<Like> getLikedUsers(User fromUser) {
        return likeRepository.findAllLikedUsers(fromUser);
    }

    @Override
    public List<Like> getLikeBetween(User fromUser, User toUser) {
        return likeRepository.findByFromUserAndToUser(fromUser, toUser);
    }
}
