package com.firstline.procedure.scheduling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @GetMapping("/greeting")
    public ModelAndView greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("name", name);
        return mv;
    }

}