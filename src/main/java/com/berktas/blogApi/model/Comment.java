package com.berktas.blogApi.model;

import com.berktas.blogApi.core.entity.AbstractTimestampEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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
