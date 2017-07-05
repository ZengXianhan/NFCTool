package com.example.corpit.testnfc.Model;

/**
 * Created by corpit on 3/7/2017.
 */

public class CheckIn {
    public CheckIn(String id, String checkInTime, String NFCNumber) {
        this.id = id;
        this.checkInTime = checkInTime;
        this.NFCNumber = NFCNumber;
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
