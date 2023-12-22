package com.example.bibliotek.Rooms;

import java.util.List;
import java.util.Optional;

public interface RoomBookingServiceInterface {
    RoomBooking findById(Long id);

    List<RoomBooking> findAll();

    void updateRoom1(String month, long id);

    void updateRoom2(String month, long id);
}
