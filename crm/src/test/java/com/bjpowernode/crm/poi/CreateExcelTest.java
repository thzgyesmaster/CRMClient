package com.bjpowernode.crm.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 使用apache-poi生成excel
 */
public class CreateExcelTest {
    public static void main(String[] args) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook(); //文件
        HSSFSheet sheet = wb.createSheet("学生列表"); //页
        HSSFRow row = sheet.createRow(0); //行 , 行号从0开始

        //写内容
        HSSFCell cell = row.createCell(0); //列 , 列号从0开始
        cell.setCellValue("学号");
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell = row.createCell(2);
        cell.setCellValue("年龄");

        //生成样式对象
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); //对齐方式:居中

        //使用sheet创建类10个HSSFRow
        for(int i = 1;i <= 10; i++){
            row = sheet.createRow(i);
            cell = row.createCell(0); //列 , 列号从0开始
            cell.setCellValue(i);
            cell = row.createCell(1);
            cell.setCellValue("Name" + i);
            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(10 + i);
        }

        OutputStream os = new FileOutputStream("/home/lifu/studentList.xls");

        wb.write(os);
        os.close();
        System.out.println("=====ok=====");
    }
}
