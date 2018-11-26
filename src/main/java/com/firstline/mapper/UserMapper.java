package com.firstline.mapper;


import com.firstline.domain.User;
import com.firstline.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto userDto);

    UserDto fromUser(User user);
}
