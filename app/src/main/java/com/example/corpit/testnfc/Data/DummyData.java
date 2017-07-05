package com.example.corpit.testnfc.Data;

import com.example.corpit.testnfc.Model.CheckIn;
import com.example.corpit.testnfc.Model.CheckInPoint;
import com.example.corpit.testnfc.Model.CheckInType;
import com.example.corpit.testnfc.Model.Customer;
import com.example.corpit.testnfc.Model.Device;
import com.example.corpit.testnfc.Model.Photo;
import com.example.corpit.testnfc.Model.Photographer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corpit on 3/7/2017.
 */

public class DummyData {
    public static List<CheckIn> getCheckInInfoList(){
        List<CheckIn> dummyInfo = new ArrayList<>();
        dummyInfo.add(new CheckIn(null,"20170307","53:01:00:02:03:0B:21"));
        dummyInfo.add(new CheckIn(null,"20170308","53:01:00:02:03:0B:1E"));
        dummyInfo.add(new CheckIn(null,"20170309","53:01:00:02:03:0B:1F"));
        dummyInfo.add(new CheckIn(null,"20170310","53:01:00:02:03:0B:25"));
        return dummyInfo;
    }

    public static List<Customer> getCustomer(){
        List<Customer> dummyInfo = new ArrayList<>();
        dummyInfo.add(new Customer("NFC001","TEST MAN 1","53:01:00:02:03:0B:21"));
        dummyInfo.add(new Customer("NFC002","TEST MAN 2","53:01:00:02:03:0B:1E"));
        dummyInfo.add(new Customer("NFC003","TEST MAN 3","53:01:00:02:03:0B:1F"));
        dummyInfo.add(new Customer("NFC004","TEST MAN 4","53:01:00:02:03:0B:25"));
        return dummyInfo;
    }

    public static List<CheckInPoint> getCheckInPointList(){
        List<CheckInPoint> dummyInfo = new ArrayList<>();
        dummyInfo.add(new CheckInPoint("1","Hotel Checkpoint 01","1"));
        dummyInfo.add(new CheckInPoint("2","Hotel Checkpoint 02","1"));
        dummyInfo.add(new CheckInPoint("3","Hotel Checkpoint 03","1"));
        dummyInfo.add(new CheckInPoint("4","Hotel Checkpoint 04","1"));
        dummyInfo.add(new CheckInPoint("5","Gala Dinner Checkpoint 01","2"));
        dummyInfo.add(new CheckInPoint("6","Gala Dinner Checkpoint 02","2"));
        dummyInfo.add(new CheckInPoint("7","Test Drive Checkpoint 01","3"));
        dummyInfo.add(new CheckInPoint("8","Test Drive Checkpoint 02","3"));
        return dummyInfo;
    }

    public static List<Device> getDeviceList(){
        List<Device> dummyInfo = new ArrayList<>();
        dummyInfo.add(new Device("1","Device 01"));
        dummyInfo.add(new Device("2","Device 02"));
        dummyInfo.add(new Device("3","Device 03"));
        dummyInfo.add(new Device("4","Device 04"));
        dummyInfo.add(new Device("5","Device 05"));
        dummyInfo.add(new Device("6","Device 06"));
        dummyInfo.add(new Device("7","Device 07"));
        return dummyInfo;
    }

    public static List<CheckInType> getCheckInTypeList(){
        List<CheckInType> dummyInfo = new ArrayList<>();
        dummyInfo.add(new CheckInType("1","Hotel Check In"));
        dummyInfo.add(new CheckInType("2","Gala Dinner Check In"));
        dummyInfo.add(new CheckInType("3","Test Drive Check In"));
        return dummyInfo;
    }

    public static List<Photo> getPhotoList(){
        return null;
    }

    public static List<Photographer> getPhotographerList(){
        List<Photographer> dummyInfo = new ArrayList<>();
        dummyInfo.add(new Photographer("1","Photographer 01"));
        dummyInfo.add(new Photographer("2","Photographer 02"));
        return dummyInfo;
    }
}
