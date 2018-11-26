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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

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
