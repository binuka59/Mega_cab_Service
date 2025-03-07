package com.megacab.model;

public class AdminDriver {
    private int id;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private  String filename;
    private String name;
    private  String nic;
    private  String phone;
    private  String email;
    private  String status;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private  String password;

    public AdminDriver(String fileName, String name, String email, String mobile, String nic, String password) {
        this.filename=fileName;
        this.name=name;
        this.email=email;
        this.phone=mobile;
        this.nic=nic;
        this.password=password;
    }
}
