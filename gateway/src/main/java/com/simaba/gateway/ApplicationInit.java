package com.simaba.gateway;

import com.simaba.gateway.drive.dao.NosqlDao;
import com.simaba.gateway.drive.entity.Vehicle;
import com.simaba.gateway.drive.util.DataToExcelUtil;
import com.simaba.gateway.drive.util.SimbaStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

@Component
@Order(1)
public class ApplicationInit implements CommandLineRunner {

    @Value("${file.sourceFile}")
    private String sourceFile; // 注入第一个配置外部文件属性

    @Value("${file.queryNum}")
    private int queryNum; // 注入第一个配置外部文件属性

    @Value("${file.ct}")
    private long cTime; // 注入第一个配置外部文件属性

    @Autowired
    private DataToExcelUtil dataToExcelUtil;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner...init");
        exportData();
    }


    private void exportData() throws Exception{
        NosqlDao nosqlDao=new NosqlDao();
        File files = new File(sourceFile);
        if(files.isDirectory()){
            System.out.println("文件夹");
            String[] filelist = files.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(sourceFile + filelist[i]);
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(readfile)));
                String vin;
                List<Vehicle> vehicleList=new ArrayList<Vehicle>();
                SimbaStringUtil.getSystemDate();
                while ( (vin = br.readLine()) != null ) {
                    if(!vin.trim().equals("")) {
                        Vehicle vehicle = nosqlDao.getVehicle(vin,cTime,queryNum);
                        if (vehicle == null) {
                            vehicle = new Vehicle();
                            vehicle.setVin(vin);
                        }
                        vehicleList.add(vehicle);
                        System.out.println(vehicle+"####"+i);
                    }
                }
                String exportFilename = readfile.getName().replace(".txt",".xls");
                dataToExcelUtil.dataToExcel(vehicleList,exportFilename);
                br.close();
                System.out.println("导出成功");
                SimbaStringUtil.getSystemDate();
            }
        }
    }
}