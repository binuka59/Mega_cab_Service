package com.megacab.service;

import com.megacab.Dao.LoginDao;
import com.megacab.model.Login;

import java.sql.SQLException;
import java.util.List;

public class LoginService {
    private static  LoginService  instance;
    private static LoginDao loginDao;

    private  LoginService()
    {
        this.loginDao = new LoginDao();
    }

    public static LoginService getInstance() {
        if (instance == null)
        {
            synchronized (LoginService.class) {
                if (instance == null) {
                    instance = new LoginService();
                }
            }
        }
        return instance;
    }

    public static List<Login> getAdminDetails() {
        return  loginDao.getAdminDetails();
    }



    public static List<Login> getupdateadmin(String id) {
        return  loginDao.getupdateadmin(id);
    }


    public void addData(Login login) {

        LoginDao.addaccount(login);
    }

    public List<Login> getAllLogin() throws SQLException {
        return loginDao.getAllLogin();
    }
}
