package com.firstline.procedure.scheduling.service;

import com.firstline.procedure.scheduling.domain.User;
import com.firstline.procedure.scheduling.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    public UserDetails loadUserByName(String name);
    public UserDto createUser(UserDto userDto);
    public void deleteUser(Long id);
    User findUserByName(String name);
}
