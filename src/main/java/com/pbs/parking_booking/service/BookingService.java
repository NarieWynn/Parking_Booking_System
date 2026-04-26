package com.pbs.parking_booking.service;

import com.pbs.parking_booking.model.Booking;
import com.pbs.parking_booking.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public Booking create(Booking booking) {
        // Nếu người dùng không gửi status, mặc định là pending
        if (booking.getBookingStatus() == null || booking.getBookingStatus().isEmpty()) {
            booking.setBookingStatus("pending");
        }
        return repository.save(booking);
    }

    public Iterable<Booking> getAll() {
        return repository.findAll();
    }

    public Booking getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find booking!"));
    }

    public Iterable<Booking> getByDriverId(Integer driverId) {
        return repository.findByDriverId(driverId);
    }

    public Booking update(Integer id, Booking updatedData) {
        Booking existingBooking = getById(id);
        
        if (updatedData.getEndTime() != null) {
            existingBooking.setEndTime(updatedData.getEndTime());
        }
        if (updatedData.getTotalPrice() != null) {
            existingBooking.setTotalPrice(updatedData.getTotalPrice());
        }
        if (updatedData.getBookingStatus() != null) {
            existingBooking.setBookingStatus(updatedData.getBookingStatus());
        }
        
        return repository.save(existingBooking);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}