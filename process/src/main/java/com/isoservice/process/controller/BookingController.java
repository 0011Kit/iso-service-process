package com.isoservice.process.controller;

import com.isoservice.process.dto.request.BookingInitRequest;
import com.isoservice.process.dto.response.BookingInfoResponse;
import com.isoservice.process.dto.response.BookingInitResponse;
import com.isoservice.process.entity.BookingEntity;
import com.isoservice.process.mapper.BookingMapper;
import com.isoservice.process.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/bookings/")
public class BookingController {
    private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    private final BookingService bookingService;

    public BookingController (BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("create")
    public ResponseEntity<BookingInitResponse> initiateBooking (@RequestBody BookingInitRequest request){
        String methodName = "[initiateBooking] - ";
        log.info("{}Received request : {}", methodName, request);
        BookingEntity initiated = bookingService.initBooking(request);
        return ResponseEntity.ok(BookingMapper.toBookingInitResponse(initiated));
    }

    @GetMapping("{id}")
    public ResponseEntity<BookingInfoResponse> getBookingById (@PathVariable UUID id){
        String methodName = "[getBookingById] - ";
        log.info("{}Received request : {}", methodName, id);
        BookingEntity bookingInfo = bookingService.getBookingById(id);
        return ResponseEntity.ok(BookingMapper.toBookingInfoResponse(bookingInfo));
    }


    @GetMapping("search/name")
    public ResponseEntity<List<BookingInfoResponse>> getBookingByClientName (@RequestParam String name){
        String methodName = "[getBookingByClientName] - ";
        log.info("{}Received request : {}", methodName, name);
        List<BookingEntity> bookingInfo = bookingService.getBookingByName(name);
        return ResponseEntity.ok(BookingMapper.toBookingInfoResponseList(bookingInfo));
    }
}
