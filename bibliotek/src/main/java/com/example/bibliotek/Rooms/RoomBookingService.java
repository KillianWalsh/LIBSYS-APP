package com.example.bibliotek.Rooms;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RoomBookingService implements RoomBookingServiceInterface {
    RoomBookingRepoInterface roomBookingRepo;

    public RoomBookingService(RoomBookingRepoInterface repo) {
        this.roomBookingRepo = repo;
    }

    @Override
    public List<RoomBooking> findAll() {
        return this.roomBookingRepo.findAll();
    }

    @Transactional
    @Override
    public void updateRoom1(String month, long id) {
        this.roomBookingRepo.updateRoom1(month, id);
    }

    @Transactional
    @Override
    public void updateRoom2(String month, long id) {
        this.roomBookingRepo.updateRoom2(month, id);
    }

    @Override
    public RoomBooking findById(Long id) {
        return this.roomBookingRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room with ID: " + id + " not found"));
    }

}
