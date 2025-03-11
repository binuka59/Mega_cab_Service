package com.megacab.model;

public class AdminBook {
    private  int id;
    private  String name;
    private  String driver;
    private  String pickupdate;
    private  String email;
    private  Integer mobile;
    private  String nic;
    private  Double price;
    private  String estimate;
    private  String additional;
    private  String  status;




    public AdminBook(Integer id,String name, String email, String mobile, String nic, String driver ,String vehicletype, String price, String estimate,String additional, String time, String date, String pickaddress, String dropaddress) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.mobile= Integer.valueOf(mobile);
        this.nic=nic;
        this.driver=driver;
        this.price= Double.valueOf(price);
        this.estimate=estimate;
        this.additional=additional;
        this.vehicletype=vehicletype;
        this.picktime=time;
        this.pickupdate=date;
        this.pickaddress=pickaddress;
        this.dropaddress=dropaddress;

    }



    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    private  String vehicletype;

    public AdminBook(Integer id ,String name, String vehicletype, String time, String date, String pickaddress, String dropaddress, String status) {
    this.id=id;
    this.name=name;
    this.vehicletype=vehicletype;
    this.picktime=time;
    this.pickupdate=date;
    this.pickaddress=pickaddress;
    this.dropaddress=dropaddress;
    this.status=status;
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

    private  String picktime;
    private  String pickaddress;
    private  String dropaddress;

}
