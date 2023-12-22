package com.example.bibliotek.Rooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomBookingRepoInterface extends JpaRepository<RoomBooking, Long> {
    @Override
    List<RoomBooking> findAll();

    //    RoomBooking findById(Long id);
    @Modifying
    @Query("update RoomBooking r set r.room1 = :monthString where r.id = :id")
    void updateRoom1(@Param(value = "monthString") String monthString, @Param(value = "id") long id);

    @Modifying
    @Query("update RoomBooking r set r.room2 = :monthString where r.id = :id")
    void updateRoom2(@Param(value = "monthString") String monthString, @Param(value = "id") long id);
}
