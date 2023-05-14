package com.berktas.blogApi.dto;

import com.berktas.blogApi.enums.Role;


public record UserDto(Long id,
                      String firstName,
                      String lastName,
                      String username,
                      String phone,
                      String email,
                      String password,
                      Role role) {


}
