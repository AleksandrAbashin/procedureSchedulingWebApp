package com.firstline.parser.impl;

import com.firstline.parser.DocService;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class DocServiceImp implements DocService {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public List<String> readDoc(String fileName) throws IOException {
        List<String> list = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(uploadPath + "/" + fileName);
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));

            Iterator<IBodyElement> bodyElementIterator = xdoc.getBodyElementsIterator();

            while (bodyElementIterator.hasNext()) {
                IBodyElement element = bodyElementIterator.next();

                if ("TABLE".equalsIgnoreCase(element.getElementType().name())) {

                    List<XWPFTable> tableList = element.getBody().getTables();
                    for (XWPFTable table : tableList) {

                        list.add(table.getText());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }

}
