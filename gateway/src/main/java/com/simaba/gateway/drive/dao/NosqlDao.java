package com.simaba.gateway.drive.dao;


import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.simaba.gateway.drive.config.CassnadraConfig;
import com.simaba.gateway.drive.entity.Vehicle;
import com.simaba.gateway.drive.util.SimbaStringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

//import org.springframework.data.cassandra.core.CassandraTemplate;

/**
 * @author ：ywq
 * @date ：Created in 2019/11/6 11:12
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
public class NosqlDao {
    Session session = CassnadraConfig.getConnectSession(CassnadraConfig.keyspace);

    private static long cTime;
    private static int queryNum;
    /**
     * realtime
     *
     * @param vin
     * @return
     * @throws Exception
     */
    public Vehicle getVehicle(String vin,long ctime,int qnum) throws Exception {
        Vehicle vehicle = null;
        Vehicle newVehicle = null;
        System.out.println("截止时间："+cTime);
        System.out.println("查询循环条数："+queryNum);
        cTime = ctime;
        queryNum = qnum;
        long ct = cTime;
        List<Long> ctList = null;
        int i = 0;
        //vehicle = getVehicle_bs(vin);
        if (vehicle == null) {
            newVehicle = getSessionVehicle(vin, getcql(vin, ct));
            while (newVehicle == null && i < queryNum) {
                ctList = getCt(getcql(vin, ct));
                if (ctList.size() > 0) {
                    long newct = ctList.get(ctList.size() - 1);
                    newVehicle = getSessionVehicle(vin, getcql(vin, newct));
                    ct = newct;
                } else {
                    break;
                }
                i++;
            }
        }
        return vehicle == null ? newVehicle : vehicle;
    }

    public Vehicle getLastCT(String vin) throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(vin);
        long ct = SimbaStringUtil.getSystemTime();
        String cql_ct = "select * from dps_realtime where v='" + vin + "' " + "and ct<" + ct + " order by ct desc limit 10";
        ResultSet execute = session.execute(cql_ct);
        for (Row rows : execute) {
            String ct1 = SimbaStringUtil.parseTime(Long.valueOf(rows.getLong("ct") + "000"), "yyyy-MM-dd HH:mm:ss");
            vehicle.setCt(ct1);
        }
        return vehicle;

    }

    public Vehicle ifExsit(String vin) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(vin);
        String cql_exsit = "select * from dps_realtime where v='" + vin + "' " + " limit 10";
        ResultSet execute = session.execute(cql_exsit);
        for (Row rows : execute) {
            String ct = SimbaStringUtil.parseTime(Long.valueOf(rows.getLong("ct") + "000"), "yyyy-MM-dd HH:mm:ss");
            if (ct != null) {
                vehicle.setExist(1);
            } else {
                vehicle.setExist(0);

            }
        }
        return vehicle;
    }

    /**
     * cql
     *
     * @param vin
     * @param ct
     * @return
     */
    public String getcql(String vin, long ct) {
        return "select * from dps_realtime where v='" + vin + "' " + "and ct<" + ct + " limit 10";
    }

    /**
     * realtime_bs
     *
     * @param vin
     * @return
     * @throws Exception
     */
    public Vehicle getVehicle_bs(String vin) throws Exception {
        Vehicle vehicle_bs = null;
        String cql_bs = "select * from dps_realtime_bs where v='" + vin + "'";
        vehicle_bs = getSessionVehicle(vin, cql_bs);

        return vehicle_bs;
    }

    /**
     * 获取分析实时数据
     *
     * @param vin
     * @param cql
     * @return
     * @throws Exception
     */
    public Vehicle getSessionVehicle(String vin, String cql) throws Exception {
        Vehicle vehicle = null;
        Vehicle newVehicle = null;
        boolean flag = true;
        ResultSet execute = session.execute(cql);
        for (Row rows : execute) {
            String data = rows.getString("data");
            String tempData = data.trim();
            String[] tempArr = tempData.split(";");
            if (tempArr.length > 88) {
                if (!"".equals(tempArr[88]) && SimbaStringUtil.getTimeLong(tempArr[88]) <= cTime) {
                    vehicle = getValidVehicle(vin, tempArr, "", 0);
                    if (vehicle != null) {
                        break;
                    }
                } else {
                    if (flag) {
                        newVehicle = getValidVehicle(vin, tempArr, "1", rows.getLong("ct"));
                        if (newVehicle != null) {
                            flag = false;
                        }
                    }
                }
            }
        }
        return vehicle == null ? newVehicle : vehicle;
    }

    /**
     * 获取ct时间
     *
     * @param cql
     * @return
     */
    public List<Long> getCt(String cql) {
        List<Long> ctList = new ArrayList<Long>();
        ResultSet execute = session.execute(cql);
        for (Row rows : execute) {
            long ct = rows.getLong("ct");
            ctList.add(ct);
        }

        return ctList;
    }

    /**
     * 获取有效数据
     *
     * @param vin
     * @param tempArr
     * @param type
     * @param ctTime
     * @return
     */
    public Vehicle getValidVehicle(String vin, String[] tempArr, String type, long ctTime) {
        Vehicle vehicle = null;
        if (Double.valueOf(tempArr[7]) > 0 && Double.valueOf(tempArr[7]) <= 300000) {
            vehicle = new Vehicle();
            vehicle.setVin(vin);
            vehicle.setGomile(tempArr[7]);
            vehicle.setLon(Double.valueOf(tempArr[43]) / 3600000);
            vehicle.setLat(Double.valueOf(tempArr[44]) / 3600000);
            if ("1".equals(type)) {
                vehicle.setIgnitionTime(SimbaStringUtil.stampToDate(ctTime));
                vehicle.setMonth(SimbaStringUtil.stampToDate(ctTime).split("-")[1]);
            } else {
                vehicle.setIgnitionTime(tempArr[88]);
                vehicle.setMonth(tempArr[88].split("-")[1]);
            }
        }
        return vehicle;
    }


}
