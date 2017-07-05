package com.example.corpit.testnfc.Model;

/**
 * Created by corpit on 4/7/2017.
 */

public class Photo {
    public String id;
    public String photoName;
    public String deviceId;
    public String photographerId;
    public String contentCustomer;

    public Photo() {
    }

    public Photo(String id, String photoName, String deviceId, String photographerId) {
        this.id = id;
        this.photoName = photoName;
        this.deviceId = deviceId;
        this.photographerId = photographerId;
    }
}
