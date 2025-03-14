package com.megacab.model;

public class Payment {
    private  int id;



    private  String name;
    private  String email;
    private  Double initialfee;
    private  Double fee;
    private  Double driverfee;
    private  String  price;
    private  String  additionalprice;
    private  String  estimateprice;
    private  String date;
    private  String driver;
    private  String  pickaddress;
    private  String dropadress;
    private  double balance;
    private  String amount;
    private  String destination;
    private  String status;



    public Payment(String bid,String amount, String pickdate, String pickaddress, String dropaddress , String status) {
        this.id= Integer.parseInt(bid);
        this.amount=amount;
        this.pickaddress=pickaddress;
        this.date=pickdate;
        this.dropadress=dropaddress;
        this.status=status;

    }


    public Payment(Integer id, String email, Double price, Double additional, String estimation, Double initial, Double fee, Double driver, String pickaddress, String dropaddress) {
         this.id=id;
         this.email=email;
         this.price= String.valueOf(price);
         this.additionalprice= String.valueOf(additional);
         this.estimateprice= estimation;
         this.initialfee=initial;
         this.fee=fee;
         this.driverfee=driver;
         this.pickaddress=pickaddress;
         this.dropadress=dropaddress;
    }

    public Payment(Integer id, String email, Double price, Double additional, String estimation, Double initial, Double fee, Double driver, String pickaddress, String dropaddress, String destination, String amount) {
        this.id=id;
        this.email=email;
        this.price= String.valueOf(price);
        this.additionalprice= String.valueOf(additional);
        this.estimateprice= estimation;
        this.initialfee=initial;
        this.fee=fee;
        this.driverfee=driver;
        this.pickaddress=pickaddress;
        this.dropadress=dropaddress;
        this.destination=destination;
        this.amount=amount;
    }

    public Payment(Double balance, String userId) {
        this.balance=balance;
        this.id= Integer.parseInt(userId);
    }

    public Payment(Integer cid) {
        this.id=cid;
    }

    public Payment(Integer payid, String amount) {
        this.id=payid;
        this.amount= amount;
    }

    public Payment(String bid, String amount, String name,String date,String status) {
        this.id= Integer.parseInt(bid);
        this.amount=amount;
        this.name=name;
        this.date=date;
        this.status=status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getInitialfee() {
        return initialfee;
    }

    public void setInitialfee(Double initialfee) {
        this.initialfee = initialfee;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getDriverfee() {
        return driverfee;
    }

    public void setDriverfee(Double driverfee) {
        this.driverfee = driverfee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAdditionalprice() {
        return additionalprice;
    }

    public void setAdditionalprice(String additionalprice) {
        this.additionalprice = additionalprice;
    }

    public String getEstimateprice() {
        return estimateprice;
    }

    public void setEstimateprice(String estimateprice) {
        this.estimateprice = estimateprice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPickaddress() {
        return pickaddress;
    }

    public void setPickaddress(String pickaddress) {
        this.pickaddress = pickaddress;
    }

    public String getDropadress() {
        return dropadress;
    }

    public void setDropadress(String dropadress) {
        this.dropadress = dropadress;
    }


}
