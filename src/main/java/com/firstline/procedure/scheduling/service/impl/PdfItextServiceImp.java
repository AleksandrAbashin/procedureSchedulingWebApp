package com.firstline.procedure.scheduling.service.impl;

import com.firstline.procedure.scheduling.domain.PatientInfo;
import com.firstline.procedure.scheduling.repos.PatientInfoRepository;
import com.firstline.procedure.scheduling.repos.PatientRepository;
import com.firstline.procedure.scheduling.service.PdfService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

@Service("itextPdf")
public class PdfItextServiceImp implements PdfService {

    @Value("${upload.path}")
    private String uploadPath;

    final PatientInfoRepository patientInfoRepository;

    final PatientRepository patientRepository;

    @Autowired
    public PdfItextServiceImp(PatientInfoRepository patientInfoRepository, PatientRepository patientRepository) {
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
  //  @Scheduled(cron = "*/5 * * * * ?")
    public void pdfFromExcel() throws Exception {

        for (PatientInfo patientInfo : patientInfoRepository.findAll()
                ) {
            FileInputStream inputDocument = new FileInputStream(new File(uploadPath + "/" + patientInfo.getFileName()));
            HSSFWorkbook xlsWorkbook = new HSSFWorkbook(inputDocument);
            HSSFSheet myWorksheet = xlsWorkbook.getSheetAt(0);
            Iterator<Row> rowIterator = myWorksheet.iterator();

            Document filePdf = new Document();
            PdfWriter.getInstance(filePdf, new FileOutputStream(uploadPath + "/" + patientInfo.getFileName() + "_info.pdf"));
            filePdf.open();

            PdfPTable myTable = new PdfPTable(2);
            PdfPCell tableCell;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getCellType() == CellType.NUMERIC) {
                        tableCell = new PdfPCell(new Phrase(Double.toString(cell.getNumericCellValue())));
                    } else {
                        tableCell = new PdfPCell(new Phrase(cell.getStringCellValue()));
                    }
                    myTable.addCell(tableCell);

                }
            }

            filePdf.add(myTable);
            filePdf.close();

            inputDocument.close();
        }
    }
}




