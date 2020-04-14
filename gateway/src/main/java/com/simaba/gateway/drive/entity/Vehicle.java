package com.simaba.gateway.drive.entity;




import java.io.Serializable;

/**
 * @author ：ywq
 * @date ：Created in 2019/9/16 13:44
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */


public class Vehicle implements Serializable {

    private String vin;
    private String IgnitionTime;
    private String gomile;
    private double Lon;
    private double Lat;
    private String month;
    private String ct;
    private int exist;

    public int getExist() {
        return exist;
    }

    public void setExist(int exist) {
        this.exist = exist;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public double getLon() {
        return Lon;
    }

    public void setLon(double lon) {
        Lon = lon;
    }

    public double getLat() {
        return Lat;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vin='" + vin + '\'' +
                ", IgnitionTime='" + IgnitionTime + '\'' +
                ", gomile='" + gomile + '\'' +
                ", Lon=" + Lon +
                ", Lat=" + Lat +
                ", month='" + month + '\'' +
                ", ct='" + ct + '\'' +
                ", exist=" + exist +
                '}';
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public String getGomile() {
        if(gomile == null) {
            gomile = "null";
        }
        return gomile;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public void setGomile(String gomile) {
        this.gomile = gomile;
    }

    public String getIgnitionTime() {
        if(IgnitionTime == null) {
            IgnitionTime = "null";
        }
        return IgnitionTime;
    }

    public void setIgnitionTime(String ignitionTime) {
        IgnitionTime = ignitionTime;
    }

    public String getMonth() {
        if(month == null) {
            month = "null";
        }
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }


}
