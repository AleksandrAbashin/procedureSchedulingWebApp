package com.firstline.procedure.scheduling.controller;

import com.firstline.procedure.scheduling.dto.StudyDto;
import com.firstline.procedure.scheduling.service.impl.StudyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudyController {
    @Autowired
    private StudyServiceImp studyServiceImp;


    @GetMapping("/study")
    public String editorPage(Model model) {
        model.addAttribute("studyDto", new StudyDto());
        return "addStudy";
    }

    @PostMapping("/study")
    public String createPatient(@ModelAttribute StudyDto studyDto) {
        studyServiceImp.createStudy(studyDto);
        return "addStudy";
    }

    @DeleteMapping(value = "/study/delete/{article_id}")
    public String detelePatient(@PathVariable("article_id") Long studyId) {
       studyServiceImp.deleteStudy(studyId);
        return "addStudy";
    }


}
