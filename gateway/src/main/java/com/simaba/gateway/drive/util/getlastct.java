package com.simaba.gateway.drive.util;

import com.simaba.gateway.drive.dao.NosqlDao;
import com.simaba.gateway.drive.entity.Vehicle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

/**
 * @author ：wangwei
 * @date ：Created in 2020-03-16 09:04
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class getlastct {
    public static void main(String[] args) throws Exception {
//        NosqlDao nosqlDao=new NosqlDao();
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:\\licheng\\vin\\M1X_1.txt"))));
//        String vin;
//        int i=0;
//        List<Vehicle> vehicleList=new ArrayList<Vehicle>();
//        SimbaStringUtil.getSystemDate();
//        while ( (vin = br.readLine()) != null ) {
//            if(!vin.trim().equals("")) {
//                Vehicle vehicle = nosqlDao.getLastCT(vin);
//                vehicleList.add(vehicle);
//                System.out.println(vehicle+"####"+i);
//                i++;
//            }
//        }
//        DataToExcelUtil.ctToExcel(vehicleList);
//        br.close();
//        System.out.println("导出成功");
//        SimbaStringUtil.getSystemDate();
//        exit(1);
    }
}
