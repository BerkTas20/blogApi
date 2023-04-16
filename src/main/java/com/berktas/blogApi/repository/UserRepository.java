package com.berktas.blogApi.repository;

import com.berktas.blogApi.core.repository.BaseRepository;
import com.berktas.blogApi.model.entity.User;
import com.berktas.blogApi.model.enums.Role;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Long countByRole(Role role);

    Optional<User> findByUsername(String username);

}
