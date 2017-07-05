package com.example.corpit.testnfc.Model;

/**
 * Created by corpit on 3/7/2017.
 */

public class CheckIn {
    public CheckIn(String checkInTime, String NFCNumber,String checkInDeviceId,String checkInTypeId,String checkInPointId) {
        this.checkInTime = checkInTime;
        this.NFCNumber = NFCNumber;
        this.checkInDeviceId = checkInDeviceId;
        this.checkInTypeId = checkInTypeId;
        this.checkInPointId = checkInPointId;
    }

    public CheckIn() {
    }

    public CheckIn(String checkInTime, String NFCNumber) {
        this.checkInTime = checkInTime;
        this.NFCNumber = NFCNumber;
    }

    public String id;
    public String checkInTime;
    public String NFCNumber;
    public String checkInDeviceId;
    public String checkInTypeId;
    public String checkInPointId;
}
