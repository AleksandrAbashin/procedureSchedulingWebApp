package com.firstline.controller;

import com.firstline.domain.PatientInfo;
import com.firstline.dto.PatientDto;
import com.firstline.dto.StudyDto;
import com.firstline.parser.DocService;
import com.firstline.parser.ExcelService;
import com.firstline.service.PatientService;
import com.firstline.service.PdfService;
import com.firstline.service.impl.Pdf;
import com.firstline.service.impl.ServiceParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
public class PatientController {

    private static int currentPage = 1;
    private static int pageSize = 5;


    @Value("${upload.path}")
    private String uploadPath;

    private final PatientService patientService;

    private final ServiceParser serviceParser;

    private final ExcelService excelService;

    private final PdfService pdfService;

    private final DocService docService;

    @Autowired
    public PatientController(PatientService patientService, ServiceParser serviceParser, ExcelService excelService, @Qualifier("pdfBox") PdfService pdfService, DocService docService) {
        this.patientService = patientService;
        this.serviceParser = serviceParser;
        this.excelService = excelService;
        this.pdfService = pdfService;
        this.docService = docService;

    }



    @GetMapping("/pdf/{id}")
    public ResponseEntity<InputStreamResource> getTime(@PathVariable Long id) {

        try {
            pdfService.pdfFromExcel();
            return pdfService.downloadPdf(id,Pdf.PDF_BOX);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping("/patient")
    public String editorPage(Model model) {
        model.addAttribute("patientDto", new PatientDto());
        return "addPatient";
    }


    @PostMapping("/patient")
    public String createPatient(@RequestParam("file") MultipartFile file,
                                @ModelAttribute @Valid PatientDto patientDto,
                                BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return "addPatient";
        } else {
            serviceParser.saveExcelFile(patientDto, file);
        }

        return "addPatient";
    }

    @GetMapping("/readDoc")
    public String readDoc(Model model) throws IOException {

        List<String> listInfo = docService.readDoc("read-test.docx");

        model.addAttribute("listInfo", listInfo);

        return "doc";

    }

    @GetMapping("/readPOI/{id}")
    public String readPOI(Model model, @PathVariable Long id) {

        try {
            List<PatientInfo> listInfo = excelService.readExcel(patientService.getPatientInfoByPatientId(id));
            model.addAttribute("contacts", listInfo);

        } catch (Exception e) {
            return "failure";
        }

        return "excel";

    }

    @GetMapping
    public String getShotListPatients(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        page.ifPresent(p -> currentPage = p);
        size.ifPresent(s -> pageSize = s);

        Page<PatientDto> patientPage = patientService.
                paginatedList(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("patientPage", patientPage);

        int totalPages = patientPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

      //  model.addAttribute("studyDto", new StudyDto());
        return "list";
    }

    @GetMapping("/patient/get/{id}")
    public ModelAndView getListOfPatientStudies(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("viewPatientInfo");

        List<StudyDto> studies = patientService.getListStudiesOfPatient(id);
        mav.addObject("studies", studies);

        return mav;
    }
}
