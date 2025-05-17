package project.tinderapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.tinderapp.entity.Message;
import project.tinderapp.entity.User;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllBySenderAndReceiverOrReceiverAndSenderOrderByTimestamp(
            User sender1, User receiver1,
            User sender2, User receiver2
    );
}