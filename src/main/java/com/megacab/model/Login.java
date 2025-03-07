package com.megacab.model;

public class Login {

    private int id;
    private String name;
    private String email;
    private String password;
    private String status;
    private String nic;
    private String mobile;


    public Login(String name, String email, String password, String nic) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.nic = nic;
        this.mobile = "";
        this.status = "Active";
    }


    public Login(String email, String password) {
        this.email = email;
        this.password = password;
        this.status = "Active";
    }



    public Login(String userId, String name, String email, String mobile, String nic) {
        try {
            this.id = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            this.id = 0; // Default or error handling
        }
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.nic = nic;
        this.status = "Active";
    }

    public Login(String aname, String mobile, String aemail, String anic, String apassword,String status) {
        this.name=aname;
        this.mobile= mobile;
        this.email=aemail;
        this.nic=anic;
        this.password=apassword;
        this.status=status;
    }

    public Login(Integer id, String name, String email, String mobile, String nic) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.mobile= mobile;
        this.nic=nic;
    }


    // Getters & Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
