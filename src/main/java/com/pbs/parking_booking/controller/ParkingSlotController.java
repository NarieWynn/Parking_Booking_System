package com.pbs.parking_booking.controller;

import com.pbs.parking_booking.model.ParkingSlot;
import com.pbs.parking_booking.service.ParkingSlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking-slots")
public class ParkingSlotController {

    private final ParkingSlotService service;

    public ParkingSlotController(ParkingSlotService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ParkingSlot> create(@RequestBody ParkingSlot slot) {
        return ResponseEntity.ok(service.create(slot));
    }

    @GetMapping
    public ResponseEntity<Iterable<ParkingSlot>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Lấy tất cả slot của một bãi đỗ cụ thể
    @GetMapping("/lot/{lotId}")
    public ResponseEntity<Iterable<ParkingSlot>> getByLotId(@PathVariable Integer lotId) {
        return ResponseEntity.ok(service.getSlotsByLotId(lotId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ParkingSlot slot) {
        try {
            return ResponseEntity.ok(service.update(id, slot));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}