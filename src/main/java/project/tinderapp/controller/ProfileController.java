package project.tinderapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.tinderapp.entity.Profile;
import project.tinderapp.entity.User;
import project.tinderapp.service.ProfileService;
import project.tinderapp.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/api/profile")
public class ProfileController {

    public final UserService userService;
    public final ProfileService profileService;

    public ProfileController(UserService userService, ProfileService profileService) {
        this.userService = userService;
        this.profileService = profileService;
    }

    @GetMapping("/{id}.getProfile")
    public ResponseEntity<Profile> getProfile(@PathVariable int id) {
        User user = userService.findById(id);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user.getProfile());
    }

    @PutMapping("/{id}/updateProfile")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody User updatedUser) {
        User existingUser = userService.findById(id);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());

        User savedUser = userService.save(existingUser);
        return ResponseEntity.ok(savedUser.getProfile());
    }

    @DeleteMapping("/{id}/depeteProfile")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userService.delete(
                id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/upload-picture")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable Long id,
                                                       @RequestParam("file") MultipartFile file) {
        Profile profile = profileService.getProfileById(id);
        if (profile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        try {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filepath = Paths.get("uploads", filename);
            Files.createDirectories(filepath.getParent());
            Files.write(filepath, file.getBytes());

            profile.setImageUrl("/uploads/" + filename);
            profileService.save(profile);

            return ResponseEntity.ok("Şəkil uğurla yükləndi");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Yükləmə xətası");
        }
    }

}
