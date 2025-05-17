package project.tinderapp.dto;

import lombok.Data;
import project.tinderapp.entity.User;

@Data
public class LikeReguestDto {

        private User fromUser;
        private User toUser;
        private boolean liked;

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
