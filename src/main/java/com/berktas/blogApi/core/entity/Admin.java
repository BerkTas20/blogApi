package com.berktas.blogApi.core.entity;




import com.berktas.blogApi.model.User;
import com.berktas.blogApi.enums.Role;
import com.berktas.blogApi.enums.UserType;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin(){
        setRole(Role.ROLE_ADMIN);
        setUserType(UserType.Admin);
    }

    public static Admin create(String firstName, String lastName, String userName, String encodedPassword){
        Admin admin = new Admin();
        admin.setRole(Role.ROLE_ADMIN);
        admin.setUserType(UserType.Admin);
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setPhone("+901112223344");
        admin.setEmail("admin@berktas.tr");
        admin.setUsername(userName);
        admin.setPassword(encodedPassword);
        return admin;
    }

}
