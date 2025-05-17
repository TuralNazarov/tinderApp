package project.tinderapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.tinderapp.entity.Profile;
import project.tinderapp.repository.ProfileRepository;
import project.tinderapp.service.ProfileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Override
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getProfileById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    @Override
    public void deleteProfileById(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public String uploadProfilePicture(Long profileId, MultipartFile file) {
        Profile profile  = profileRepository.findById(profileId).get();
        if (profile == null) {
            throw new RuntimeException("Profile not found");
        }

        try {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get("uploads", filename);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            profile.setImageUrl("/uploads/" + filename);
            profileRepository.save(profile);
            return profile.getImageUrl();
        } catch (IOException e) {
            throw new RuntimeException("Could not upload file", e);
        }
    }
}
