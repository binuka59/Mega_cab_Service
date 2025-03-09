package com.megacab.model;

import java.time.LocalDate;

public class Booking {


    private  int id;
    private int userId;
    private  int vehicleid;
    private  String name;
    private  String email;
    private  int mobile;
    private  String nic;
    private  String vehicletype;
    private  String driver;
    private  String pickupdate;
    private  String picktime;
    private  String pickaddress;
    private  String dropaddress;






    public Booking(String vehicle, String driver, String pickdate, String picktime, String pickaddress, String dropaddress) {
        this.vehicletype=vehicle;
        this.driver=driver;
        this.pickupdate=pickdate;
        this.picktime=picktime;
        this.pickaddress=pickaddress;
        this.dropaddress=dropaddress;
    }

    public Booking(Integer id, String name, String vehicle, String time, String pickaddress, String dropaddress) {
        this.id=id;
        this.name=name;
        this.vehicletype=vehicle;
        this.picktime=time;
        this.pickaddress=pickaddress;
        this.dropaddress=dropaddress;
    }

    public Booking(String bid, String vehicle, String time, String pickaddress, String dropaddress) {
        this.id= Integer.parseInt(bid);
        this.vehicletype=vehicle;
        this.picktime=time;
        this.pickaddress=pickaddress;
        this.dropaddress=dropaddress;
    }

    public Booking(String bid, String vehicle, String time, LocalDate pickDate, String pickaddress, String dropaddress) {
        this.id= Integer.parseInt(bid);
        this.vehicletype=vehicle;
        this.picktime=time;
        this.pickupdate= String.valueOf(pickDate);
        this.pickaddress=pickaddress;
        this.dropaddress=dropaddress;
    }


    public static void add(Booking booking) {
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    public int getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(int vehicleid) {
        this.vehicleid = vehicleid;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPickupdate() {
        return pickupdate;
    }

    public void setPickupdate(String pickupdate) {
        this.pickupdate = pickupdate;
    }

    public String getPicktime() {
        return picktime;
    }

    public void setPicktime(String picktime) {
        this.picktime = picktime;
    }

    public String getPickaddress() {
        return pickaddress;
    }

    public void setPickaddress(String pickaddress) {
        this.pickaddress = pickaddress;
    }

    public String getDropaddress() {
        return dropaddress;
    }

    public void setDropaddress(String dropaddress) {
        this.dropaddress = dropaddress;
    }



}
