package com.example.corpit.testnfc.Model;

/**
 * Created by corpit on 4/7/2017.
 */

public class CheckInPoint {
    public String id;
    public String pointName;
    public String checkInTypeId;

    public CheckInPoint() {
    }

    public CheckInPoint(String id, String deviceName,String checkInTypeId) {
        this.id = id;
        this.pointName = deviceName;
        this.checkInTypeId =checkInTypeId;
    }
}
