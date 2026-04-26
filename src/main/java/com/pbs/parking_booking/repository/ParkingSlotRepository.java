package com.pbs.parking_booking.repository;

import com.pbs.parking_booking.model.ParkingSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSlotRepository extends CrudRepository<ParkingSlot, Integer> {
    
    Iterable<ParkingSlot> findByLotId(Integer lotId);
}