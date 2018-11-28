package com.firstline.service.impl;

import com.firstline.domain.PatientInfo;
import com.firstline.repos.PatientInfoRepository;
import com.firstline.service.PdfDownloadService;
import com.firstline.service.PdfService;
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

    private final PatientInfoRepository patientInfoRepository;

    private final PdfDownloadService pdfDownloadService;

    @Autowired
    public PdfItextServiceImp(PatientInfoRepository patientInfoRepository, PdfDownloadService pdfDownloadService) {
        this.patientInfoRepository = patientInfoRepository;
        this.pdfDownloadService = pdfDownloadService;
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadPdf(Long id, Pdf pdf) throws IOException {
        return pdfDownloadService.download(id, pdf);
    }

    @Override
    //  @Scheduled(cron = "*/5 * * * * ?")
    public void pdfFromExcel() throws Exception {

        for (PatientInfo patientInfo : patientInfoRepository.findAll()
                ) {
            try (FileInputStream inputDocument = new FileInputStream(new File(uploadPath + "/" + patientInfo.getFileName()));
                 HSSFWorkbook xlsWorkbook = new HSSFWorkbook(inputDocument)) {
                HSSFSheet myWorksheet = xlsWorkbook.getSheetAt(0);
                Document filePdf = new Document();
                PdfWriter.getInstance(filePdf, new FileOutputStream(uploadPath + "/" + patientInfo.getFileName() + Pdf.ITEXT.toString()));
                filePdf.open();

                PdfPTable myTable = new PdfPTable(2);
                PdfPCell tableCell;

                Iterator<Row> rowIterator = myWorksheet.iterator();
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
            }
        }
    }


}




