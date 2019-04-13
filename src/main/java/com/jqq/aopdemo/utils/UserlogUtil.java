package com.jqq.aopdemo.utils;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

/**
 * 记录用户登入，登出时间并记录到xlsx
 */
@Component
public class UserlogUtil {


    public void userlog(List<String> logInfo) throws FileNotFoundException {
        String path = "D:/excel/userlog.xlsx";
        File excel = new File(path);
        /*
         * 判断父目录是否存在
         * 	不存在：先创建父目录文件夹，再创建指定的文件。
         */
        if (!excel.getParentFile().exists()) {
            try {
                excel.getParentFile().mkdirs();
                excel.createNewFile();
                System.out.println("创建文件夹和文件成功（路径：D:/excel/userlog.xlsx)!");


                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFCellStyle styleMain=workbook.createCellStyle();
                styleMain.setAlignment(HorizontalAlignment.CENTER);
                Sheet sheet = workbook.createSheet();
                sheet.setColumnWidth(1,25*256);
                sheet.setColumnWidth(2,25*256);
                Row row = sheet.createRow(0);
                row.createCell(0).setCellValue("用户");
                row.createCell(1).setCellValue("登入时间");
                row.createCell(2).setCellValue("登出时间");

                String filename = "D:/excel/userlog.xlsx";

                FileOutputStream out = new FileOutputStream(filename);
                try {
                    workbook.write(out);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (!excel.exists()) {
            try {
                excel.createNewFile();
                System.out.println("创建文件成功（路径：D:/excel/userlog.xlsx)!");

                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFCellStyle styleMain=workbook.createCellStyle();
                styleMain.setAlignment(HorizontalAlignment.CENTER);
                Sheet sheet = workbook.createSheet();
                sheet.setColumnWidth(1,25*256);
                sheet.setColumnWidth(2,25*256);
                Row row = sheet.createRow(0);
                row.createCell(0).setCellValue("用户");
                row.createCell(1).setCellValue("登入时间");
                row.createCell(2).setCellValue("登出时间");

                String filename = "D:/excel/userlog.xlsx";

                FileOutputStream out = new FileOutputStream(filename);
                try {
                    workbook.write(out);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        try {
            FileInputStream fs;
            fs = new FileInputStream(excel);
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            Sheet sheet = workbook.getSheetAt(0);

            int lastRowNum = sheet.getLastRowNum();
            Row row = sheet.createRow(lastRowNum + 1);

            for (int i = 0; i < logInfo.size(); i++) {
                row.createCell(i).setCellValue(logInfo.get(i));
            }

            FileOutputStream out = new FileOutputStream(excel);
            workbook.write(out);
            workbook.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
