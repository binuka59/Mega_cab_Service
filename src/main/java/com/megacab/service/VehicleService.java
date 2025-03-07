package com.megacab.service;

import com.megacab.Dao.LoginDao;
import com.megacab.Dao.VehicleDao;
import com.megacab.model.Login;
import com.megacab.model.Vehicle;

import java.sql.SQLException;
import java.util.List;

public class VehicleService {

    private static   VehicleService instance;
    private  VehicleDao vehicleDao;

    private  VehicleService()
    {
        this.vehicleDao = new VehicleDao();
    }

    public static VehicleService getInstance() {
        if (instance == null)
        {
            synchronized (VehicleService.class) {
                if (instance == null) {
                    instance = new VehicleService();
                }
            }
        }
        return instance;
    }

    public static List<Vehicle> getAllVehicle() throws SQLException {
        return VehicleDao.getAllVehicle();
    }

    public static List<Vehicle> getAllVehicletype(String vehiclename) {
        return VehicleDao.getAllVehicletype(vehiclename);
    }

    public static List<Vehicle> getdisplayvehicle() {
        return VehicleDao.getdisplayvehicle() ;
    }

    public static List<Vehicle> getvehicleDetail(String editid) {
        return  VehicleDao.getVehicleDetail(editid);
    }


    public void addData(Vehicle vehicle) {

        VehicleDao.addData(vehicle);
    }




}
