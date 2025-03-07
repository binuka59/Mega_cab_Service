package com.megacab.service;

import com.megacab.Dao.LoginDao;
import com.megacab.Dao.RegisterDao;
import com.megacab.model.Login;
import com.megacab.model.Register;

public class RegisterService {
    private static  RegisterService  instance;
    private RegisterDao registerDao;
    private  RegisterService()
    {this.registerDao = new RegisterDao();}

    public static RegisterService getInstance() {
        if (instance == null)
        {
            synchronized (RegisterService.class) {
                if (instance == null) {
                    instance = new RegisterService();
                }
            }
        }
        return instance;
    }

    public void addData(Register register) {
        RegisterDao.addData(register);
    }
}
