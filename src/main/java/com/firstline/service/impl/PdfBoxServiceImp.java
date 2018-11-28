package com.firstline.service.impl;

import com.firstline.domain.PatientInfo;
import com.firstline.repos.PatientInfoRepository;
import com.firstline.repos.PatientRepository;
import com.firstline.service.PdfDownloadService;
import com.firstline.service.PdfService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Service("pdfBox")
public class PdfBoxServiceImp implements PdfService {
    @Value("${upload.path}")
    private String uploadPath;

    private final PatientInfoRepository patientInfoRepository;

    private PatientRepository patientRepository;

    private PdfDownloadService pdfDownloadService;

    @Autowired
    public PdfBoxServiceImp(PatientInfoRepository patientInfoRepository, PatientRepository patientRepository, PdfDownloadService pdfDownloadService) {
        this.patientInfoRepository = patientInfoRepository;
        this.patientRepository = patientRepository;
        this.pdfDownloadService = pdfDownloadService;
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadPdf(Long id, Pdf pdf) throws IOException {
        return pdfDownloadService.download(id, pdf);
    }

    @Override
    // @Scheduled(cron = "*/15 * * * * ?")
    public void pdfFromExcel() throws Exception {

        final int SheetOffsetX = 100;
        final int SheetOffsetY = 700;
        final int FontSize = 12;

        final int TableColumnOffsetX = -250;
        final int TableColumnOffsetY = -20;

        final int TableColumnNumericOffsetX = 250;
        final int TableColumnNumericOffsetY = 0;

        for (PatientInfo patientInfo : patientInfoRepository.findAll()
                ) {
            try (FileInputStream inputDocument =
                         new FileInputStream(new File(uploadPath + "/" + patientInfo.getFileName()));
                 HSSFWorkbook xlsWorkbook = new HSSFWorkbook(inputDocument);
                 PDDocument document = new PDDocument()
            ) {
                HSSFSheet myWorksheet = xlsWorkbook.getSheetAt(0);
                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                PDPageContentStream contentStream = new PDPageContentStream(document, page);

                PDType1Font font = PDType1Font.COURIER;
                contentStream.setFont(font, FontSize);
                contentStream.beginText();
                contentStream.newLineAtOffset(SheetOffsetX, SheetOffsetY);

                Iterator<Row> rowIterator = myWorksheet.iterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (cell.getCellType() == CellType.NUMERIC) {

                            contentStream.showText(Double.toString(cell.getNumericCellValue()));
                            contentStream.newLineAtOffset(TableColumnOffsetX, TableColumnOffsetY);
                        } else if (!cell.getStringCellValue().isEmpty()) {
                            contentStream.showText(cell.getStringCellValue());
                            contentStream.newLineAtOffset(TableColumnNumericOffsetX, TableColumnNumericOffsetY);
                        }
                    }
                }
                contentStream.endText();
                contentStream.close();
                document.save(uploadPath + "/" + patientInfo.getFileName() + Pdf.PDF_BOX.toString());

            }
        }
    }

}
