package com.firstline.procedure.scheduling.controller;

import com.firstline.procedure.scheduling.dto.PatientDto;
import com.firstline.procedure.scheduling.service.impl.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {
    @Autowired
    private PatientServiceImp patientServiceImp;


    @GetMapping("/patient")
    public String editorPage(Model model) {
        model.addAttribute("patientDto", new PatientDto());
        return "addPatient";
    }

    @PostMapping("/patient")
    public String createPatient(@ModelAttribute PatientDto patientDto) {
        patientServiceImp.createPatient(patientDto);
        return "addPatient";
    }

    @DeleteMapping(value = "/patient/delete/{article_id}")
    public String detelePatient(@PathVariable("article_id") Long patientId) {
        patientServiceImp.deletePatient(patientId);
        return "addPatient";
    }


}
