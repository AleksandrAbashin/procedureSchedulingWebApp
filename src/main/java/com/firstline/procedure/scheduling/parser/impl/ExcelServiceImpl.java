package com.firstline.procedure.scheduling.parser.impl;

import com.firstline.procedure.scheduling.domain.PatientInfo;
import com.firstline.procedure.scheduling.parser.ExcelService;
import com.firstline.procedure.scheduling.service.PatientInfoService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private PatientInfoService patientInfoService;

    @Override
    public List<PatientInfo> readExcel(PatientInfo patientInfo) throws IOException {
        List<PatientInfo> contacts = new ArrayList<PatientInfo>();

        POIFSFileSystem fileSystem = new POIFSFileSystem(new File("/home/aleksandr/IdeaProjects/uploads/poi.xls"));
        HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
        HSSFSheet sheet = workBook.getSheetAt(0);


        for (Row row : sheet) {

            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        patientInfo.setDisease(cell.getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        patientInfo.setPhone((int) cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        break;
                    case FORMULA:
                        break;

                }

            }

            patientInfoService.createPatientInfo(patientInfo);
            contacts.add(patientInfo);

        }

        return contacts;

    }
}

        /*if (workBook != null){
            workBook.close();

        Iterator<Row> rows = sheet.iterator();

        if (rows.hasNext()) {
            rows.next();
        }

        while (rows.hasNext()) {
            HSSFRow row = (HSSFRow) rows.next();

            HSSFCell addressCell = row.getCell(1);

            if (addressCell != null) {

                PatientInfo patientInfo = new PatientInfo();
                patientInfo.setAddress(addressCell.getStringCellValue());

                patientInfoService.createPatientInfo(patientInfo);
                contacts.add(patientInfo);

            }
        }
        fileSystem.close();
        return contacts;*/


