package com.megacab.model;

public class Forget {


    public Forget(String codeStr, String email) {
        this.verify = codeStr;
        this.email = email;
    }

    public Forget(String newpass) {
        this.password = newpass;
    }


    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getVerify() {

        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private static String email;
    private String verify;



    private  String password;

}
