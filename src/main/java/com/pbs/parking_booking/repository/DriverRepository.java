package com.pbs.parking_booking.repository;

import com.pbs.parking_booking.model.User; 
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends CrudRepository<User, Integer> {
    
    @Modifying
    @Query("INSERT INTO Driver (driver_id, license_number) VALUES (:driverId, :licenseNumber)")
    void insertDriver(@Param("driverId") Integer driverId, @Param("licenseNumber") String licenseNumber);
}