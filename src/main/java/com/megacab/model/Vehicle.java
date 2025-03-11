package com.megacab.model;

public class Vehicle {

    private Integer id;
    private String name;
    private double inicharge;
    private String type;
    private String filename;
    private double bookfee;
    private String Status;
    private double price;
    private String additionalprice;
    private String estimate;

    public Vehicle(Integer cid) {
        this.id=cid;
    }

    public Vehicle(Integer userId, String vname) {
        this.id=userId;
        this.name=vname;
    }

    public double getDriverfee() {
        return driverfee;
    }

    public void setDriverfee(double driverfee) {
        this.driverfee = driverfee;
    }

    private double driverfee;

    public Vehicle(String image, String vname, double price, String status) {
        filename=image;
        this.name=vname;
        this.price= price;
        this.Status = status;
    }
//getdisplayvehile
    public Vehicle(int id, String image, String vname, double price, String addtionalprice, String estimation, String status) {
        this.id=id;
        this.filename=image;
        this.name=vname;
        this.price=price;
        this.additionalprice=addtionalprice;
        this.estimate=estimation;
        this.Status=status;
    }
//getAllVehicletype
    public Vehicle(String image, String vname, double price, String addtionalprice, String estimation, String status) {

        this.filename=image;
        this.name=vname;
        this.price=price;
        this.additionalprice=addtionalprice;
        this.estimate=estimation;
        this.Status=status;
    }
//vehicleController updateData
    public Vehicle(Integer id ,String fileName, String name, String price, String addinprice, String estimate, String vehicletype ,String status) {
        this.id=id;
        this.filename=fileName;
        this.name=name;
        this.price= Double.parseDouble(price);
        this.additionalprice=addinprice;
        this.estimate=estimate;
        this.type=vehicletype;
        this.Status=status;

    }
//adddata
    public Vehicle(String fileName, String name, String price, String addinprice, String estimate, String vehicletype, String status) {
        this.filename=fileName;
        this.name=name;
        this.price= Double.parseDouble(price);
        this.additionalprice=addinprice;
        this.estimate=estimate;
        this.type=vehicletype;
        this.Status=status;
    }
//getVehicleDetail
    public Vehicle(String image, String vname, double price, String addtionalprice, String estimation, String type, String status) {
        this.filename=image;
        this.name=vname;
        this.price= price;
        this.additionalprice=addtionalprice;
        this.estimate=estimation;
        this.type=type;
        this.Status=status;
    }


    public String getAdditionalprice() {
        return additionalprice;
    }

    public void setAdditionalprice(String additionalprice) {
        this.additionalprice = additionalprice;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }



    public Vehicle(String name, String image, double initial, double fee, double driver) {
        this.name = name;
        this.filename = image;
        this.inicharge = initial;
        this.bookfee = fee;
        this.driverfee=driver;

    }


    public Vehicle(String vehicle) {
        this.type = vehicle;
    }

    public Vehicle( String fileName, String name, String price, double addinprice, String estimate, String vehicletype, String status) {

        this.filename=fileName;
        this.name=name;
        this.price= Double.parseDouble(price);
        this.additionalprice = String.valueOf(addinprice);
        this.estimate = estimate;
        this.type=vehicletype;
        this.Status = status;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInicharge() {
        return inicharge;
    }

    public void setInicharge(double inicharge) {
        this.inicharge = inicharge;
    }



    public double getBookfee() {
        return bookfee;
    }

    public void setBookfee(double bookfee) {
        this.bookfee = bookfee;
    }



    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }




    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
