package com.berktas.blogApi.model.entity;

import com.berktas.blogApi.core.entity.AbstractTimestampEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment extends AbstractTimestampEntity {

    private String description;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
