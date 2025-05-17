package project.tinderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.tinderapp.entity.Profile;

@Repository

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
