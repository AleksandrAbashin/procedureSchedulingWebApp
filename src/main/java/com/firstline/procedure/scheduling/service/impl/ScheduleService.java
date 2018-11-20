package com.firstline.procedure.scheduling.service.impl;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

@Service
public class ScheduleService {

    @Value("${upload.path}")
    private String uploadPath;


   // @Scheduled(cron="*/5 * * * * ?")
    public void pdfFromExcel(String filename) throws Exception {


        FileInputStream input_document = new FileInputStream(new File(uploadPath + "/" + filename));

        HSSFWorkbook my_xls_workbook = new HSSFWorkbook(input_document);

        HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);

        Iterator<Row> rowIterator = my_worksheet.iterator();

        Document filePdf = new Document();
        PdfWriter.getInstance(filePdf, new FileOutputStream(uploadPath + "/" +"info.pdf"));
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

        input_document.close();
    }
}




