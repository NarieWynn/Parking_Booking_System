package com.pbs.parking_booking.repository;

import com.pbs.parking_booking.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {
    Iterable<Booking> findByDriverId(Integer driverId);
    Iterable<Booking> findBySlotId(Integer slotId);
}