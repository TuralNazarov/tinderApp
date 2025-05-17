package project.tinderapp.service;

import org.springframework.web.multipart.MultipartFile;
import project.tinderapp.entity.Profile;
import project.tinderapp.entity.User;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Profile save(Profile profile);
    public List<Profile> findAll();
    public Profile getProfileById(Long id);
    public void deleteProfileById(Long id);
    String uploadProfilePicture(Long profileId, MultipartFile file);
}

