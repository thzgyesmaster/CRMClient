package com.bjpowernode.crm.commons.utils;

import com.bjpowernode.crm.workbench.domain.Activity;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * 操作excel文件
 */
public class HSSFUtils {

    public static HSSFWorkbook getActivityList(List<Activity> activities){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);//第一行

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("id");

        cell = row.createCell(1);
        cell.setCellValue("所有者");

        cell = row.createCell(2);
        cell.setCellValue("名称");

        cell = row.createCell(3);
        cell.setCellValue("开始日期");

        cell = row.createCell(4);
        cell.setCellValue("结束日期");

        cell = row.createCell(5);
        cell.setCellValue("成本");

        cell = row.createCell(6);
        cell.setCellValue("描述");

        cell = row.createCell(7);
        cell.setCellValue("创建时间");

        cell = row.createCell(8);
        cell.setCellValue("创建者");

        cell = row.createCell(9);
        cell.setCellValue("修改时间");

        cell = row.createCell(10);
        cell.setCellValue("修改者");


        if(activities!= null && activities.size() > 0){
            Activity activity = null;
            for (int i = 0; i < activities.size(); i++) {
                activity = activities.get(i);
                row = sheet.createRow(i + 1);
                //每行有11列
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());

                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());

                cell = row.createCell(2);
                cell.setCellValue(activity.getName());

                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());

                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());

                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());

                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());

                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());

                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());

                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());

                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
        return wb;
    }

    public static String getCellValueForStr(HSSFCell cell){
        String ret="";
        if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
            ret=cell.getStringCellValue();
        }else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
            ret=cell.getNumericCellValue()+"";
        }else if(cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
            ret=cell.getBooleanCellValue()+"";
        }else if(cell.getCellType()==HSSFCell.CELL_TYPE_FORMULA){
            ret=cell.getCellFormula();
        }else{
            ret="";
        }

        return ret;
    }
}
