package com.megacab.service;

import com.megacab.Dao.BookingDao;
import com.megacab.model.AdminBook;
import com.megacab.model.Booking;

import java.util.List;

public class BookingService {
    private static   BookingService instance;
    private static BookingDao bookingDao;

    private BookingService()
    {

        this.bookingDao = new BookingDao();
    }
    public static BookingService getInstance() {
        if (instance == null)
        {
            synchronized (BookingService.class) {
                if (instance == null) {
                    instance = new BookingService();
                }
            }
        }
        return instance;
    }

    public static List<Booking> getAllBooking() {
        return bookingDao.getAllBooking();
    }

    public static List<AdminBook> getAllBookingdetails() {
        return  bookingDao.getAllBookingdetails();
    }

    public static List<AdminBook> getbookdetails(String seeid) {
        return  bookingDao.getbookdetails(seeid);
    }
}
