package com.pbs.parking_booking.service;

import com.pbs.parking_booking.model.ParkingLot;
import com.pbs.parking_booking.repository.ParkingLotRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {

    private final ParkingLotRepository repository;

    public ParkingLotService(ParkingLotRepository repository) {
        this.repository = repository;
    }

    public ParkingLot create(ParkingLot parkingLot) {
        return repository.save(parkingLot);
    }

    public Iterable<ParkingLot> getAll() {
        return repository.findAll();
    }

    public ParkingLot getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy bãi đỗ xe!"));
    }

    public ParkingLot update(Integer id, ParkingLot updatedData) {
        ParkingLot existingLot = getById(id);
        existingLot.setLotName(updatedData.getLotName());
        existingLot.setTotalSlot(updatedData.getTotalSlot());
        existingLot.setProvince(updatedData.getProvince());
        existingLot.setDistrict(updatedData.getDistrict());
        existingLot.setStreet(updatedData.getStreet());
        
        return repository.save(existingLot);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}