package com.example.bibliotek.Rooms;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/RoomBooking", produces = "application/json; charset=UTF-8")
//@RequiredArgsConstructor
public class RoomBookingController {

    private final RoomBookingService service;

    public RoomBookingController(RoomBookingService service) {
        this.service = service;
    }

    @GetMapping("/findById")
    @ResponseBody
    public RoomBooking findById(@RequestParam long id) {
        return this.service.findById(id);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<RoomBooking> findAll() {
        return this.service.findAll();
    }

    @PutMapping("/updateRoom1")
    @ResponseBody
    public void updateRoom1(@RequestParam String month, @RequestParam long id) {
        this.service.updateRoom1(month, id);
    }

    @PutMapping("/updateRoom2")
    @ResponseBody
    public void updateRoom2(@RequestParam String month, @RequestParam long id) {
        this.service.updateRoom2(month, id);
    }
}
