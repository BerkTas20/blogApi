package com.berktas.blogApi.core.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractTimestampEntity extends BaseEntity {

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;

    @PrePersist
    protected void onCreate() {
        updatedDateTime = createdDateTime = LocalDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDateTime = LocalDateTime.now(ZoneOffset.UTC);
    }

}
