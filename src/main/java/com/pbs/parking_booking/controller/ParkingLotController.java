package com.pbs.parking_booking.controller;

import com.pbs.parking_booking.model.ParkingLot;
import com.pbs.parking_booking.service.ParkingLotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking-lots")
public class ParkingLotController {

    private final ParkingLotService service;

    public ParkingLotController(ParkingLotService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ParkingLot> create(@RequestBody ParkingLot parkingLot) {
        return ResponseEntity.ok(service.create(parkingLot));
    }

    @GetMapping
    public ResponseEntity<Iterable<ParkingLot>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // Lấy 1 bãi đỗ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Cập nhật bãi đỗ
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ParkingLot parkingLot) {
        try {
            return ResponseEntity.ok(service.update(id, parkingLot));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa bãi đỗ
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}