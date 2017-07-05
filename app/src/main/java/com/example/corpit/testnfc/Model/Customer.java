package com.example.corpit.testnfc.Model;

/**
 * Created by corpit on 3/7/2017.
 */

public class Customer {
    public String id;
    public String name;
    public String NFCNumber;
    public String foodPreference;
    public String email;
    public String contactNumber;
    public String jobTitle;
    public String galaDinnerTime;
    public String circuitTime;
    public String isGalaDinnerCheckIn;
    public String isCheckIn;
    public String quizResult;

    public Customer() {
    }

    public Customer(String id, String name,String NFCNumber) {
        this.id = id;
        this.name = name;
        this.NFCNumber = NFCNumber;
    }
}
