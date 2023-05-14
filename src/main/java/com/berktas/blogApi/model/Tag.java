package com.berktas.blogApi.model;

import com.berktas.blogApi.core.entity.AbstractTimestampEntity;
import lombok.*;

import javax.persistence.Entity;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tag extends AbstractTimestampEntity {

    private String name;

    public Tag(String name) {
        super();
        this.name = name;
    }

}
