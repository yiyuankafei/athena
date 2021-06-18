package com.athena.common.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ReadExcelUtil {

    public static void main(String[] args) throws Exception {

        String filePath = "C:\\Users\\Administrator\\Desktop\\美梦成真自有曲库及MV 清单0617.xlsx";
        FileInputStream is = new FileInputStream(filePath);
        Workbook workbook = null;
        if (filePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(is);
        } else if (filePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(is);
        }
        Sheet sheet = workbook.getSheetAt(0);
        System.out.println(sheet.getLastRowNum());
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            double s3 = row.getCell(0).getNumericCellValue();
            String s5 = row.getCell(4).getStringCellValue();

            System.out.println(s3);
            System.out.println(s5);
        }
    }

}
