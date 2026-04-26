package com.pbs.parking_booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

@Data
@Table("Parking_Slot")
public class ParkingSlot {
    @Id
    private Integer slotId;
    private String slotNumber;
    private String type; // VIP, NORMAL, DISABLED
    private String isAvailable; // 'Y' or 'N'
    private Integer lotId;
}