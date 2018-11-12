package com.firstline.procedure.scheduling.controller;

import com.firstline.procedure.scheduling.domain.User;
import com.firstline.procedure.scheduling.dto.UserDto;
import com.firstline.procedure.scheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {


    @Autowired
    private UserService userService;


    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(@Valid @ModelAttribute UserDto userDto, BindingResult result) {

        User userExisting = userService.findUserByName(userDto.getName());
        if (userExisting != null){
         //   result.rejectValue("name", null, "There is already an account registered with that user name");
            return "/login";
        }

        if (result.hasErrors()){
            return "registration";
        }

        userService.createUser(userDto);

        return "redirect:/login";
    }
}
