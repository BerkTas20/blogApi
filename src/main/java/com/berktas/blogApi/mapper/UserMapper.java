package com.berktas.blogApi.mapper;

import com.berktas.blogApi.core.mapper.BaseMapper;
import com.berktas.blogApi.dto.UserDto;
import com.berktas.blogApi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDto, User> {
}
