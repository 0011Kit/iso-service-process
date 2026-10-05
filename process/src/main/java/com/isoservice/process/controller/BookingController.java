package com.isoservice.process.controller;

import com.isoservice.process.dto.request.BookingInitRequest;
import com.isoservice.process.entity.BookingEntity;
import com.isoservice.process.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController (BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingEntity> initiateBooking (@RequestBody BookingInitRequest request){
        BookingEntity initiated = bookingService.initBooking(request);
        return ResponseEntity.ok(initiated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingEntity> getBooking (@PathVariable String id){
        BookingEntity bookingInfo = bookingService.getBookingById(id);
        return ResponseEntity.ok(bookingInfo);
    }
}
