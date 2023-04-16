package com.berktas.blogApi.model.dto;

import com.berktas.blogApi.model.enums.Role;

public record UserDto(Long id,
                      String firstName,
                      String lastName,
                      String username,
                      String phone,
                      String email,
                      String password,
                      Role role) {


}
