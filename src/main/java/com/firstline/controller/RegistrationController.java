package com.firstline.controller;

import com.firstline.domain.User;
import com.firstline.dto.UserDto;
import com.firstline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {


    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(@Valid UserDto userDto, BindingResult result, Model model) {

        if (userDto.getPassword() != null && !userDto.getPassword().equals(userDto.getPassword2())) {
            model.addAttribute("passwordError", "Password are different!");

            return "registration";
        }

        if (result.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(result);
            model.addAttribute(errors);
            return "registration";
        }

        User userExisting = userService.findUserByName(userDto.getName());
        if (userExisting != null) {
            model.addAttribute("userError", "This user already exist!");
            return "registration";
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.createUser(userDto);

        return "redirect:/login";

    }
}
