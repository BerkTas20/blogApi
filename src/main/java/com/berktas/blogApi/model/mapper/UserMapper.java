package com.berktas.blogApi.model.mapper;

import com.berktas.blogApi.core.mapper.BaseMapper;
import com.berktas.blogApi.model.dto.LikeDto;
import com.berktas.blogApi.model.dto.UserDto;
import com.berktas.blogApi.model.entity.Like;
import com.berktas.blogApi.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDto, User> {
}
