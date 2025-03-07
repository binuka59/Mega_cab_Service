package com.megacab.service;

import com.megacab.Dao.ForgetDao;
import com.megacab.Dao.LoginDao;
import com.megacab.Dao.VehicleDao;
import com.megacab.model.Forget;
import com.megacab.model.Login;
import com.megacab.model.Vehicle;

import java.util.List;

public class ForgetService {
    private static  ForgetService  instance;
    private ForgetDao forgetDao;

    private  ForgetService()
    {
        this.forgetDao = new ForgetDao();
    }

    public static ForgetService getInstance() {
        if (instance == null)
        {
            synchronized (LoginService.class) {
                if (instance == null) {
                    instance = new ForgetService();
                }
            }
        }
        return instance;
    }
    public void addcode(Forget forget) {
        ForgetDao.addData(forget);
    }

    public static List<Vehicle> getAllVehicletype(String vehiclename) {
        return VehicleDao.getAllVehicletype(vehiclename);
    }
}
