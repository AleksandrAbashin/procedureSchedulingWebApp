package com.firstline.procedure.scheduling.parser.impl;

import com.firstline.procedure.scheduling.parser.ExcelPOIHelper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelPOIHelperImpl implements ExcelPOIHelper {

    @Override
    public Map<Integer, List<String>> readExcel(String fileLocation) throws IOException {

        Map<Integer, List<String>> data = new HashMap<>();
        FileInputStream file = new FileInputStream(new File(fileLocation));

        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        data.get(i)
                                .add(cell.getRichStringCellValue()
                                        .getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            data.get(i)
                                    .add(cell.getDateCellValue() + "");
                        } else {
                            data.get(i)
                                    .add((int) cell.getNumericCellValue() + "");
                        }
                        break;
                    case BOOLEAN:
                        data.get(i)
                                .add(cell.getBooleanCellValue() + "");
                        break;
                    case FORMULA:
                        data.get(i)
                                .add(cell.getCellFormula() + "");
                        break;
                    default:
                        data.get(i)
                                .add(" ");
                }
            }
            i++;
        }
        if (workbook != null) {
            workbook.close();
        }
        return data;
    }
}