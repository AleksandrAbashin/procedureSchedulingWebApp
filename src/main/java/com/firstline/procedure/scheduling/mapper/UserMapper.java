package com.firstline.procedure.scheduling.mapper;


import com.firstline.procedure.scheduling.domain.User;
import com.firstline.procedure.scheduling.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto userDto);

    UserDto fromUser(User user);
}
