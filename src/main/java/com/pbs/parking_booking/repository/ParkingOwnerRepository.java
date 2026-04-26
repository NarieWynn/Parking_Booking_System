package com.pbs.parking_booking.repository;

import com.pbs.parking_booking.model.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingOwnerRepository extends CrudRepository<User, Integer> {

    @Modifying
    @Query("INSERT INTO Parking_Owner (owner_id, business_name, business_contact) VALUES (:ownerId, :businessName, :businessContact)")
    void insertOwner(@Param("ownerId") Integer ownerId, @Param("businessName") String businessName, @Param("businessContact") String businessContact);
}