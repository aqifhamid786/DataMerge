package com.aqif.schwarzit.workbook;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class WBManager {

    private Workbook workbook = null;

    public WBManager(String sourceFilePath) {
        initialize(sourceFilePath);
    }

    public void initialize (String sourceFilePath) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(sourceFilePath));
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Workbook getWorkbook() {
        return workbook;
    }
}
