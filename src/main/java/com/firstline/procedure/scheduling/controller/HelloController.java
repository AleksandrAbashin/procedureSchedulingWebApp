package com.firstline.procedure.scheduling.controller;

import com.firstline.procedure.scheduling.service.PatientService;
import com.firstline.procedure.scheduling.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    PatientService patientService;

    @Autowired
    StudyService studyService;

    @GetMapping("/main")
    public ModelAndView greeting(String name, Model model) {
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("patients", patientService.getAllPatients());

        return mv;

    }
}
