package com.isoservice.process.service;

import com.isoservice.process.controller.BookingController;
import com.isoservice.process.domain.BookingStatus;
import com.isoservice.process.dto.request.BookingInitRequest;
import com.isoservice.process.entity.BookingEntity;
import com.isoservice.process.entity.StaffEntity;
import com.isoservice.process.exception.ResourceNotFoundException;
import com.isoservice.process.mapper.BookingMapper;
import com.isoservice.process.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class BookingService {
    private static final Logger log = LoggerFactory.getLogger(BookingService.class);
    private final BookingRepository bookingRepository;
    private final StaffService staffService;

    public BookingService (
            BookingRepository bookingRepository,
            StaffService staffService
    ){
        this.bookingRepository = bookingRepository;
        this.staffService = staffService;
    }


    public BookingEntity initBooking(BookingInitRequest bookingRequest){
        //ttejfnsmrjmv
        String methodName = "[initBooking] - ";
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setClientName(bookingRequest.clientName());
        bookingEntity.setEmail(bookingRequest.email());
        bookingEntity.setNumberOfPax(bookingRequest.numberOfPax());
        bookingEntity.setAppointmentDate(bookingRequest.appointmentDate());
        UUID photographerId = staffService.getStuffInfoByName(bookingRequest.photographerName()).stream()
                .filter(record ->
                            record.getEmploymentCategory() == StaffEntity.EmploymentCategory.PHOTOGRAPHER &&
                            record.getEmploymentStatus() == StaffEntity.EmploymentStatusType.ACTIVE)
                .map(StaffEntity::getId)
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException("No photographer found with name = " + bookingRequest.photographerName()));
        bookingEntity.setPhotographerId(photographerId);
        BookingMapper.applyDomainStatus(bookingEntity,new BookingStatus.Submitted());
        log.info("{}Saving to DB : {}", methodName, bookingEntity);
        return bookingRepository.save(bookingEntity);
    }

    public BookingEntity getBookingById (UUID id){
        log.info("[getBookingById] - Accessing DB.");
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with Id: " + id));
    }

    public List<BookingEntity> getBookingByName (String name){
        log.info("[getBookingByName] - Accessing DB.");
        List<BookingEntity> result = bookingRepository.findAllByClientNameContainingIgnoreCase(name);
        if(result.isEmpty()){
            throw new ResourceNotFoundException("Booking not found with Name: " + name);
        }
        return result;
    }
}
