package com.pbs.parking_booking.service;

import com.pbs.parking_booking.model.ParkingSlot;
import com.pbs.parking_booking.repository.ParkingSlotRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingSlotService {

    private final ParkingSlotRepository repository;

    public ParkingSlotService(ParkingSlotRepository repository) {
        this.repository = repository;
    }

    public ParkingSlot create(ParkingSlot slot) {
        //  'Y' or 'N' follow schema
        if (slot.getIsAvailable() == null || (!slot.getIsAvailable().equals("Y") && !slot.getIsAvailable().equals("N"))) {
            slot.setIsAvailable("Y"); // mặc định Y nếu truyền sai
        }
        return repository.save(slot);
    }

    public Iterable<ParkingSlot> getAll() {
        return repository.findAll();
    }

    public ParkingSlot getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find the slot!"));
    }

    public Iterable<ParkingSlot> getSlotsByLotId(Integer lotId) {
        return repository.findByLotId(lotId);
    }

    public ParkingSlot update(Integer id, ParkingSlot updatedData) {
        ParkingSlot existingSlot = getById(id);
        existingSlot.setSlotNumber(updatedData.getSlotNumber());
        existingSlot.setType(updatedData.getType());
        
        if (updatedData.getIsAvailable() != null && (updatedData.getIsAvailable().equals("Y") || updatedData.getIsAvailable().equals("N"))) {
            existingSlot.setIsAvailable(updatedData.getIsAvailable());
        }
        
        return repository.save(existingSlot);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}