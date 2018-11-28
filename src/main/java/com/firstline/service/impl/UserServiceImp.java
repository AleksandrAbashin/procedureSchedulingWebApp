package com.firstline.service.impl;

import com.firstline.domain.User;
import com.firstline.dto.UserDto;
import com.firstline.mapper.UserMapper;
import com.firstline.repos.UserRepository;
import com.firstline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImp(PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public UserDetails loadUserByName(String name) {
        return null;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userRepository.findUserByName(userDto.getName());
        if (user != null) {
            return null;
        }else {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

            user = userRepository.save(userMapper.toUser(userDto));
        }
        return userMapper.fromUser(user);
    }

    @Override
    public void deleteUser(Long id) {

    }

 }
