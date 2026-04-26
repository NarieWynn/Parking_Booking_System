package com.pbs.parking_booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Table("Booking")
public class Booking {
    @Id
    private Integer bookingId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Float totalPrice;
    private String bookingStatus; // 'pending' is origin
    private Integer driverId;
    private Integer slotId;
}