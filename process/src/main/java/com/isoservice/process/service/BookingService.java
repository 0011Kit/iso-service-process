package com.isoservice.process.service;

import com.isoservice.process.domain.BookingStatus;
import com.isoservice.process.dto.request.BookingInitRequest;
import com.isoservice.process.entity.BookingEntity;
import com.isoservice.process.mapper.BookingMapper;
import com.isoservice.process.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService (BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    public BookingEntity initBooking(BookingInitRequest bookingRequest){
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setClientName(bookingRequest.clientName());
        bookingEntity.setEmail(bookingRequest.email());
        bookingEntity.setNumberOfPax(bookingRequest.numberOfPax());
        bookingEntity.setAppointmentDate(bookingRequest.appointmentDate());

        BookingMapper.applyDomainStatus(bookingEntity,new BookingStatus.Submitted());

        return bookingRepository.save(bookingEntity);
    }

    public BookingEntity getBookingById (String id){
        return bookingRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Booking not found with Id: " + id));
    }
}
