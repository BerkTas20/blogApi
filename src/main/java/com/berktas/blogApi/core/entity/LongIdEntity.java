package com.berktas.blogApi.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class LongIdEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
