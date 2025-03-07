package com.megacab.service;

import com.megacab.Dao.DriverDao;
import com.megacab.model.Driver;

import java.sql.SQLException;
import java.util.List;

public class DriverService {

    private static  DriverService  instance;
    private DriverDao driverDao;


    private  DriverService()
    {
        this.driverDao = new DriverDao();
    }
    public static DriverService getInstance() {
        if (instance == null)
        {
            synchronized (DriverService.class) {
                if (instance == null) {
                    instance = new DriverService();
                }
            }
        }
        return instance;
    }
    public static List<Driver> getAllDrivers() throws SQLException {
        return DriverDao.getAllDrivers();
    }

    public static List<Driver> getAllDriversDetails(String deditid) {
        return  DriverDao.getAllDriversDetails(deditid);
    }
}
