package com.example.bibliotek.Rooms;

import lombok.*;

import javax.persistence.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@Entity(name = "libsys.room_booking")
//@Table(name = "libsys.room_booking")
@Table
@Entity
public class RoomBooking {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String room1;
    private String room2;
}
