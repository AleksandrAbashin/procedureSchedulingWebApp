package com.firstline.procedure.scheduling.controller;

import com.firstline.procedure.scheduling.dto.UserDto;
import com.firstline.procedure.scheduling.mapper.UserMapper;
import com.firstline.procedure.scheduling.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute UserDto userDto ) {

        userRepository.save(userMapper.toUser(userDto));

        return "redirect:/login";
    }
}
