package com.isoservice.process.repository;

import com.isoservice.process.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, String>{
    List<BookingEntity> findByStatusTypeAndStatusTimestampBefore(
            BookingEntity.StatusType statusType,
            Instant cutoff);
}
