package com.isoservice.process.repository;

import com.isoservice.process.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID>{

    List<BookingEntity> findAllByClientNameContainingIgnoreCase(String clientName);

    List<BookingEntity> findByStatusTypeAndStatusTimestampBefore(
            BookingEntity.BookingStatusType bookingStatusType,
            Instant cutoff);


}
