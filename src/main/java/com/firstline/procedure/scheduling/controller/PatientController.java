package com.firstline.procedure.scheduling.controller;

import com.firstline.procedure.scheduling.dto.PatientDto;
import com.firstline.procedure.scheduling.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;


    @GetMapping
    public String editorPage(Model model) {
        model.addAttribute("patientDto", new PatientDto());
        return "addPatient";
    }

    @PostMapping
    public String createPatient(@ModelAttribute PatientDto patientDto) {
        patientService.createPatient(patientDto);
        return "addPatient";
    }

    @GetMapping("/list")
    public String getListPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "list";
    }

    @GetMapping("/get/{id}")
    public ModelAndView getPatient(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("viewPatientInfo");

        PatientDto patientDto = patientService.getPatientById(id);

        mav.addObject("patientDto", patientService.getPatientById(id));
        return mav;

    }

}
