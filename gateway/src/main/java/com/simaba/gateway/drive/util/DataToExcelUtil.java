package com.simaba.gateway.drive.util;

import com.simaba.gateway.drive.entity.Vehicle;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author ：wangwei
 * @date ：Created in 2019-11-14 12:52
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class DataToExcelUtil {

    @Value("${file.exportFile}")
    private String exportFile; // 注入第一个配置外部文件属性

    public void dataToExcel(List<Vehicle> list,String file) {
        try {
            String fileName = exportFile+file;
            System.out.println(fileName);
            FileUtil.createFile(fileName);
            FileOutputStream fos = new FileOutputStream(new File(fileName));
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("S15");
            sheet.setDefaultColumnWidth(20);
            HSSFRow row = sheet.createRow(0);
            HSSFCellStyle style = wb.createCellStyle();
            int i = 0;
            HSSFCell cell = row.createCell(i++);
            cell.setCellValue("VIN");
            cell.setCellStyle(style);
            cell = row.createCell(i++);
            cell.setCellValue("最后一次下电的有效时间");
            cell.setCellStyle(style);
            cell = row.createCell(i++);
            cell.setCellValue("最后一次下电的有效里程");
            cell.setCellStyle(style);
            cell = row.createCell(i++);
            cell.setCellValue("最后一次下电的位置");
            cell.setCellStyle(style);
            cell = row.createCell(i++);
            cell.setCellValue("行驶的月份");
            cell.setCellStyle(style);
            cell = row.createCell(i++);
            cell.setCellValue("CT");
            cell.setCellStyle(style);
            cell = row.createCell(i++);
            cell.setCellValue("数据库是否有数据");
            cell.setCellStyle(style);
            for (int j = 0; j < list.size(); j++) {
                int k = 0;
                row = sheet.createRow(j + 1);
                Vehicle vehicle = list.get(j);
                row.createCell(k++).setCellValue(vehicle.getVin());
                row.createCell(k++).setCellValue(vehicle.getIgnitionTime());
                row.createCell(k++).setCellValue(vehicle.getGomile());
                row.createCell(k++).setCellValue("(" + vehicle.getLon() + "," + vehicle.getLat() + ")");
                row.createCell(k++).setCellValue(vehicle.getMonth());
                row.createCell(k++).setCellValue(vehicle.getCt());
                row.createCell(k++).setCellValue(vehicle.getExist());
            }
            wb.write(fos);
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ctToExcel(List<Vehicle> list) {
        try {
            String fileName = "D:\\licheng\\excel\\M1X_1.xls";
            FileUtil.createFile(fileName);
            FileOutputStream fos = new FileOutputStream(new File(fileName));
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("M1X_1");
            sheet.setDefaultColumnWidth(20);
            HSSFRow row = sheet.createRow(0);
            HSSFCellStyle style = wb.createCellStyle();
            int i = 0;
            HSSFCell cell = row.createCell(i++);
            cell.setCellValue("VIN");
            cell.setCellStyle(style);
            cell = row.createCell(i++);
            cell.setCellValue("CT");
            cell.setCellStyle(style);
            for (int j = 0; j < list.size(); j++) {
                int k = 0;
                row = sheet.createRow(j + 1);
                Vehicle vehicle = list.get(j);
                row.createCell(k++).setCellValue(vehicle.getVin());
                row.createCell(k++).setCellValue(vehicle.getCt());
            }
            wb.write(fos);
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
