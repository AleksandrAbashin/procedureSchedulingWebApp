package com.firstline.procedure.scheduling.service.impl;

import com.firstline.procedure.scheduling.domain.PatientInfo;
import com.firstline.procedure.scheduling.repos.PatientInfoRepository;
import com.firstline.procedure.scheduling.repos.PatientRepository;
import com.firstline.procedure.scheduling.service.PdfService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Service
public class PdfBoxServiceImp implements PdfService {
    @Value("${upload.path}")
    private String uploadPath;

    final PatientInfoRepository patientInfoRepository;

    final PatientRepository patientRepository;

    @Autowired
    public PdfBoxServiceImp(PatientInfoRepository patientInfoRepository, PatientRepository patientRepository) {
        this.patientInfoRepository = patientInfoRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadPdf(Long id) throws IOException {
        String fileName = patientRepository.getById(id).getPatientInfo().getFileName();

        File file = new File(uploadPath + "/" + fileName + "_info.pdf");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
                .body(resource);
    }

    @Override
    @Scheduled(cron = "*/5 * * * * ?")
    public void pdfFromExcel() throws Exception {
        for (PatientInfo patientInfo : patientInfoRepository.findAll()
                ) {
            FileInputStream inputDocument = new FileInputStream(new File(uploadPath + "/" + patientInfo.getFileName()));
            HSSFWorkbook xlsWorkbook = new HSSFWorkbook(inputDocument);
            HSSFSheet myWorksheet = xlsWorkbook.getSheetAt(0);
            Iterator<Row> rowIterator = myWorksheet.iterator();


            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
        //    contentStream.moveTo(-100,-700);
            PDType1Font font = PDType1Font.COURIER;
            contentStream.setFont(font, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(10, 700);
         //   contentStream.moveTo();


            float stringWidth = font.getStringWidth("");

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getCellType() == CellType.NUMERIC) {

                        contentStream.showText(Double.toString(cell.getNumericCellValue()));
                        contentStream.newLineAtOffset(-120, -20);
                    } else if(!cell.getStringCellValue().isEmpty()) {
                        contentStream.showText(cell.getStringCellValue());
                        contentStream.newLineAtOffset(120, 0);
                    }

                }

            }

            contentStream.endText();
            contentStream.close();

            document.save(uploadPath + "/" + patientInfo.getFileName() + "_infoBox.pdf");
            document.close();

        }
    }

}
