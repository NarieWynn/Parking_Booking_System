package com.pbs.parking_booking.repository;

import com.pbs.parking_booking.model.ParkingLot;
import org.springframework.data.repository.CrudRepository; //CrudRepository
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends CrudRepository<ParkingLot, Integer> {
    // Kế thừa CrudRepository là đã tự có đủ hàm save(), findById(), findAll(), deleteById()
}