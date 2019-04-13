package com.jqq.aopdemo.utils;

import com.jqq.aopdemo.entity.Alumni;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class DownloadUtil {
    public void dowload(List<Alumni> logInfo) {
        String path = "D:/excel/汇总.xlsx";
        File excel = new File(path);

        //删除原有文件，以免append重复内容
        if(excel.exists()){
            excel.delete();
        }
        /*
         * 判断父目录是否存在
         * 	不存在：先创建父目录文件夹，再创建指定的文件。
         */
        if (!excel.getParentFile().exists()) {
            try {
                excel.getParentFile().mkdirs();
                excel.createNewFile();
                System.out.println("创建文件夹和文件成功（路径：D:/excel/汇总.xlsx)!");


                XSSFWorkbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet();
                sheet.setColumnWidth(3,13*256);
                sheet.setColumnWidth(4,8*256);
                sheet.setColumnWidth(5,8*256);
                sheet.setColumnWidth(6,17*256);
                sheet.setColumnWidth(7,17*256);
                sheet.setColumnWidth(8,17*256);
                sheet.setColumnWidth(9,17*256);
                sheet.setColumnWidth(10,17*256);
                sheet.setColumnWidth(11,24*256);
                sheet.setColumnWidth(12,17*256);
                Row row = sheet.createRow(0);
                row.createCell(0).setCellValue("ID");
                row.createCell(1).setCellValue("姓名");
                row.createCell(2).setCellValue("性别");
                row.createCell(3).setCellValue("生日");
                row.createCell(4).setCellValue("入学年份");
                row.createCell(5).setCellValue("毕业年份");
                row.createCell(6).setCellValue("工作城市/地区");
                row.createCell(7).setCellValue("工作单位");
                row.createCell(8).setCellValue("职位");
                row.createCell(9).setCellValue("手机号码");
                row.createCell(10).setCellValue("邮箱");
                row.createCell(11).setCellValue("微信号");


                String filename = "D:/excel/汇总.xlsx";

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
                System.out.println("创建文件成功（路径：D:/excel/汇总.xlsx)!");

                XSSFWorkbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet();
                sheet.setColumnWidth(3,13*256);
                sheet.setColumnWidth(4,8*256);
                sheet.setColumnWidth(5,8*256);
                sheet.setColumnWidth(6,17*256);
                sheet.setColumnWidth(7,17*256);
                sheet.setColumnWidth(8,17*256);
                sheet.setColumnWidth(9,17*256);
                sheet.setColumnWidth(10,17*256);
                sheet.setColumnWidth(11,24*256);
                sheet.setColumnWidth(12,17*256);
                Row row = sheet.createRow(0);
                row.createCell(0).setCellValue("ID");
                row.createCell(1).setCellValue("姓名");
                row.createCell(2).setCellValue("性别");
                row.createCell(3).setCellValue("生日");
                row.createCell(4).setCellValue("入学年份");
                row.createCell(5).setCellValue("毕业年份");
                row.createCell(6).setCellValue("工作城市/地区");
                row.createCell(7).setCellValue("工作单位");
                row.createCell(8).setCellValue("职位");
                row.createCell(9).setCellValue("手机号码");
                row.createCell(10).setCellValue("邮箱");
                row.createCell(11).setCellValue("微信号");


                String filename = "D:/excel/汇总.xlsx";

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

            int lastRowNum = sheet.getLastRowNum()+1;


            for (int i = 0; i < logInfo.size(); i++) {
                Row row = sheet.createRow(lastRowNum);
                lastRowNum++;
                row.createCell(0).setCellValue(logInfo.get(i).getId());
                row.createCell(1).setCellValue(logInfo.get(i).getName());
                row.createCell(2).setCellValue(logInfo.get(i).getSex());
                row.createCell(3).setCellValue(logInfo.get(i).getBirthday());
                row.createCell(4).setCellValue(logInfo.get(i).getInSchoolDate());
                row.createCell(5).setCellValue(logInfo.get(i).getOutSchoolDate());
                row.createCell(6).setCellValue(logInfo.get(i).getWorkingCity());
                row.createCell(7).setCellValue(logInfo.get(i).getWorkingUnit());
                row.createCell(8).setCellValue(logInfo.get(i).getJob());
                row.createCell(9).setCellValue(logInfo.get(i).getPhoneNum());
                row.createCell(10).setCellValue(logInfo.get(i).getEmail());
                row.createCell(11).setCellValue(logInfo.get(i).getWechat());

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
