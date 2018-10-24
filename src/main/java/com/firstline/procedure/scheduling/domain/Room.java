package com.firstline.procedure.scheduling.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long roomId;

    @Column(name = "room_name")
    String roomName;
}
