package com.pbs.parking_booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

@Data
@Table("Parking_Lot")
public class ParkingLot {
    @Id
    private Integer lotId;
    private String lotName;
    private Integer totalSlot;
    private String province;
    private String district;
    private String street;
    private Integer ownerId;
}