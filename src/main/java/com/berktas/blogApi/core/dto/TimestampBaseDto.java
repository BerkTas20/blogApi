package com.berktas.blogApi.core.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public abstract class TimestampBaseDto extends BaseDto {
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}
