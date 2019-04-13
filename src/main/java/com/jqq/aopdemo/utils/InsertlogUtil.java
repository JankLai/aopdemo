package com.jqq.aopdemo.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 记录表单信息，并插入到xlsx
 */
@Component
public class InsertlogUtil {

    public void insertlog(List<String> logInfo) {
        String path = "D:/excel/insertlog.xlsx";
        File excel = new File(path);
        /*
         * 判断父目录是否存在
         * 	不存在：先创建父目录文件夹，再创建指定的文件。
         */
        if (!excel.getParentFile().exists()) {
            try {
                excel.getParentFile().mkdirs();
                excel.createNewFile();
                System.out.println("创建文件夹和文件成功（路径：D:/excel/insertlog.xlsx)!");


                XSSFWorkbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet();
                sheet.setColumnWidth(2,13*256);
                sheet.setColumnWidth(3,8*256);
                sheet.setColumnWidth(4,8*256);
                sheet.setColumnWidth(5,17*256);
                sheet.setColumnWidth(6,17*256);
                sheet.setColumnWidth(7,17*256);
                sheet.setColumnWidth(8,17*256);
                sheet.setColumnWidth(9,17*256);
                sheet.setColumnWidth(10,24*256);
                sheet.setColumnWidth(11,17*256);
                Row row = sheet.createRow(0);
                row.createCell(0).setCellValue("姓名");
                row.createCell(1).setCellValue("性别");
                row.createCell(2).setCellValue("生日");
                row.createCell(3).setCellValue("入学年份");
                row.createCell(4).setCellValue("毕业年份");
                row.createCell(5).setCellValue("工作城市/地区");
                row.createCell(6).setCellValue("工作单位");
                row.createCell(7).setCellValue("职位");
                row.createCell(8).setCellValue("手机号码");
                row.createCell(9).setCellValue("邮箱");
                row.createCell(10).setCellValue("微信号");


                String filename = "D:/excel/insertlog.xlsx";

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
                System.out.println("创建文件成功（路径：D:/excel/insertlog.xlsx)!");

                XSSFWorkbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet();
                sheet.setColumnWidth(2,13*256);
                sheet.setColumnWidth(3,8*256);
                sheet.setColumnWidth(4,8*256);
                sheet.setColumnWidth(5,17*256);
                sheet.setColumnWidth(6,17*256);
                sheet.setColumnWidth(7,17*256);
                sheet.setColumnWidth(8,17*256);
                sheet.setColumnWidth(9,17*256);
                sheet.setColumnWidth(10,24*256);
                sheet.setColumnWidth(11,17*256);
                Row row = sheet.createRow(0);
                row.createCell(0).setCellValue("姓名");
                row.createCell(1).setCellValue("性别");
                row.createCell(2).setCellValue("生日");
                row.createCell(3).setCellValue("入学年份");
                row.createCell(4).setCellValue("毕业年份");
                row.createCell(5).setCellValue("工作城市/地区");
                row.createCell(6).setCellValue("工作单位");
                row.createCell(7).setCellValue("职位");
                row.createCell(8).setCellValue("手机号码");
                row.createCell(9).setCellValue("邮箱");
                row.createCell(10).setCellValue("微信号");


                String filename = "D:/excel/insertlog.xlsx";

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
