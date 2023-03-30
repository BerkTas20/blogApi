package com.berktas.blogApi.core.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity extends LongIdEntity implements Serializable {

}