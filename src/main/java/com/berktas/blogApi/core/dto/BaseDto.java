package com.berktas.blogApi.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@NoArgsConstructor
@Setter
public abstract class BaseDto {

    private Long id;

}
