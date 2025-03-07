package com.megacab.model;

public class Driver {

   private int id;
   private String name;
   private  String nic;
   private  String phone;
   private  String email;
   private  String status;

    public Driver(String fileName) {
        this.filename=fileName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private  String password;

//    getAllDrivers()
    public Driver(Integer id,String image, String name, String nic, String mobile, String email, String status) {
        this.id=id;
        this.filename=image;
        this.name=name;
        this.email= email;
        this.phone= mobile;
        this.nic=nic;
        this.status=status;
    }

    public Driver(String image, String name, String nic, String mobile, String email, String status) {
        this.filename=image;
        this.name=name;
        this.email= email;
        this.phone= mobile;
        this.nic=nic;
        this.status=status;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    private  String filename;

//adddriver
    public Driver(String fileName, String name, String email, String mobile, String nic) {
        this.filename=fileName;
        this.name=name;
        this.email= email;
        this.phone= mobile;
        this.nic=nic;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Driver(String email, String password) {
    }
}
