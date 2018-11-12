package com.firstline.procedure.scheduling.service.impl;

import com.firstline.procedure.scheduling.domain.User;
import com.firstline.procedure.scheduling.dto.UserDto;
import com.firstline.procedure.scheduling.mapper.UserMapper;
import com.firstline.procedure.scheduling.repos.UserRepository;
import com.firstline.procedure.scheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements /*UserDetailsService,*/ UserService {

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

   /* @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByName(s);
    }*/
}
