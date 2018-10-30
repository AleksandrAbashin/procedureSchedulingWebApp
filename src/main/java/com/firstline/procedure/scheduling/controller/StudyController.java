package com.firstline.procedure.scheduling.controller;

import com.firstline.procedure.scheduling.dto.StudyDto;
import com.firstline.procedure.scheduling.service.impl.StudyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("/study")
public class StudyController {
    @Autowired
    private StudyServiceImp studyServiceImp;


    @GetMapping("/")
    public String editorPage(Model model) {
        model.addAttribute("studyDto", new StudyDto());
        return "addStudy";
    }


    @PostMapping("/")
    public String createStudy(@ModelAttribute StudyDto studyDto) {
        studyServiceImp.createStudy(studyDto);
        return "addStudy";
    }

   /* @GetMapping("/update")
    public String editorPageTwo(Model model) {
        model.addAttribute("studyDto", studyServiceImp.getStudyById());
        return "updateStudy";
    }*/

    @PutMapping("/update/{id}")
    public String updateStudy(@PathVariable Long id, Model model) {
        model.addAttribute("studyDto",studyServiceImp.getStudyById(id));
      //  studyServiceImp.updateStudy(studyDto);
        return "updateStudy";
    }

    @DeleteMapping(value = "/study/delete/{article_id}")
    public String deletePatient(@PathVariable("article_id") Long studyId) {
       studyServiceImp.deleteStudy(studyId);
        return "addStudy";
    }


}
