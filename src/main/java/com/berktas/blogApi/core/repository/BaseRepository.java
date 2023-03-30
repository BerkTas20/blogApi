package com.berktas.blogApi.core.repository;

import com.berktas.blogApi.core.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<Entity extends BaseEntity> extends JpaRepository<Entity, Long> {
}
