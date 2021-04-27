package com.example.thehive.device_setup;

import java.io.Serializable;

public class Device implements Serializable {

    private String name;
    private String ip;
    private int gpio;
    private int imageid;

    Device(String name, String ip, int gpio,int imageid) {
        this.gpio = gpio;
        this.ip = ip;
        this.name = name;
        this.imageid= imageid;
    }
    public String get_Name() {

        return name;
    }

    public void set_Name(String mName) {

        this.name = mName;
    }

    public String getIp() {

        return ip;
    }

    public void setIp(String mRelease)
    {
        this.ip = mRelease;
    }
    public int getGpio()
    {
        return gpio;
    }
    public void setGpio(int gpio)
    {
        this.gpio=gpio;
    }
    public int getImageid()
    {
        return imageid;
    }
}
