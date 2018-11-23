package com.firstline.procedure.scheduling.parser.impl;

import com.firstline.procedure.scheduling.domain.PatientInfo;
import com.firstline.procedure.scheduling.parser.ExcelService;
import com.firstline.procedure.scheduling.repos.PatientInfoRepository;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelServiceImpl implements ExcelService {

    @Value("${upload.path}")
    private String uploadPath;


    @Autowired
    private PatientInfoRepository patientInfoRepository;

    @Override
    public List<PatientInfo> readExcel(PatientInfo patientInfo) throws IOException {
        List<PatientInfo> contacts = new ArrayList<PatientInfo>();

        POIFSFileSystem fileSystem = new POIFSFileSystem(new File(uploadPath + "/" + patientInfo.getFileName()));
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

            contacts.add(patientInfoRepository.save(patientInfo));

        }

        return contacts;

    }
}