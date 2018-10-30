package com.firstline.procedure.scheduling.controller;

import com.firstline.procedure.scheduling.dto.PatientDto;
import com.firstline.procedure.scheduling.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String list(Model model) {
         model.addAttribute("patients", patientService.getAllPatients());
        return "list";
    }
/*
    @DeleteMapping(value = "/patient/delete/{article_id}")
    public String detelePatient(@PathVariable("article_id") Long patientId) {
        patientServiceImp.deletePatient(patientId);
        return "addPatient";
    }
*/

}
