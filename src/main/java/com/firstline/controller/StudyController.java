package com.firstline.procedure.scheduling.controller;

import com.firstline.procedure.scheduling.dto.StudyDto;
import com.firstline.procedure.scheduling.service.PatientService;
import com.firstline.procedure.scheduling.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/study")
public class StudyController {
    @Autowired
    private StudyService studyService;
    @Autowired
    private PatientService patientService;

    @GetMapping
    public String editorPage(Model model) {
        model.addAttribute("studyDto", new StudyDto());
        model.addAttribute("patients", patientService.getAllPatients());
        return "addStudy";
    }


    @PostMapping
    public String createStudy(@ModelAttribute StudyDto studyDto) {
        studyService.createStudy(studyDto);
        return "addStudy";
    }

    @GetMapping("/update/{id}")
    public String updateStudy(@PathVariable Long id, Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("studyDto", studyService.updateStudy(studyService.getStudyById(id)));
        return "addStudy";
    }
}
