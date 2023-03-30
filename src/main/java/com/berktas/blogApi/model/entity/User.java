package com.berktas.blogApi.model.entity;

import com.berktas.blogApi.core.entity.AbstractTimestampEntity;
import com.berktas.blogApi.model.enums.UserType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE )
@Table
public class User extends AbstractTimestampEntity {
    private String firstName;
    private String lastName;
    private String username;
    private String phone;
    private String email;
    private String password;
    private String role;

    @Column(name = "user_type", updatable = false, insertable = false)
    @Enumerated(EnumType.STRING)
    private com.berktas.blogApi.model.enums.UserType userType;
    @Lob
    private byte[] profilePhoto;
    protected void setUserType(UserType value) {
        userType = value;
    }

//    public void updatePassword(String password) {
//        password = new BCryptPasswordEncoder().encode(password);
//    }

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

//    @ManyToMany(cascade = CascadeType.REMOVE)
//    @JoinTable(
//            name = "users_authorities",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "authority_id")
//    )
//    private List<Author> authorities;
}
