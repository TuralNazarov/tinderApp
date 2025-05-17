package project.tinderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.tinderapp.entity.Like;
import project.tinderapp.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {


    @Query("SELECT l FROM Like l WHERE l.likedUser = :user")
    List<Like> findAllLikedUsers(@Param("user") User user);

    List<Like> findByFromUserAndToUser(User fromUser, User toUser);
}