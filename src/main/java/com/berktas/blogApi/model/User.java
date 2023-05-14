package com.berktas.blogApi.model;

import com.berktas.blogApi.controller.requests.SaveAndUpdateUserRequest;
import com.berktas.blogApi.core.entity.AbstractTimestampEntity;
import com.berktas.blogApi.core.security.SpringContext;
import com.berktas.blogApi.enums.Role;
import com.berktas.blogApi.enums.UserType;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;


@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE )
@Table
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractTimestampEntity {

    private String firstName;
    private String lastName;
    private String username;
    private String phone;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Role role = Role.ROLE_USER;

    @Column(name = "user_type", updatable = false, insertable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Lob
    private byte[] profilePhoto;

    @Lob
    private byte[] coverPhoto;

    boolean isBlocked = false;

    protected void setUserType(UserType value) {
        userType = value;
    }

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public static User create(SaveAndUpdateUserRequest saveUserRequest) {
        User user = new User();
        user.setUsername(saveUserRequest.getUsername());
        user.setRole(saveUserRequest.getRole());
        user.setFirstName(saveUserRequest.getFirstName());
        user.setLastName(saveUserRequest.getLastName());
        user.changePassword(saveUserRequest.getPassword());
        user.setEmail(saveUserRequest.getEmail());
        return user;
    }

    public static User update(SaveAndUpdateUserRequest updateUserRequest) {
        User user = SpringContext.getCurrentUser();
        user.setUsername(updateUserRequest.getUsername());
        user.setRole(updateUserRequest.getRole());
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setEmail(updateUserRequest.getEmail());
        return user;
    }

    public void changePassword(String rawPassword) {
        password = new BCryptPasswordEncoder().encode(rawPassword);
    }

    public void updateProfilePhoto(MultipartFile file) throws IOException {
        this.profilePhoto = file.getBytes();
    }

    public void updateCoverPhoto(MultipartFile file) throws IOException {
        this.coverPhoto = file.getBytes();
    }

    public void deleteProfilePhoto() {
        this.profilePhoto = null;
    }

    public void deleteCoverPhoto() {
        this.coverPhoto = null;
    }

}
