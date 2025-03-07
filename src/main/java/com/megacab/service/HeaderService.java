package com.megacab.service;

import com.megacab.Dao.HeaderDao;
import com.megacab.model.Header;

import java.util.List;

public class HeaderService {
    private  static  HeaderService  instance;
    private static HeaderDao headerDao;

    private HeaderService()
    {
        this.headerDao = new HeaderDao();
    }

    public static HeaderService getInstance() {
        if(instance == null)
        {
            synchronized (HeaderService.class){
                if (instance==null)
                {
                    instance = new HeaderService();
                }
            }
        }
        return instance;
    }


    public static List<Header> getAllheader() {
        int userId = 17;
        return HeaderDao.getAllheader(userId);
    }
}