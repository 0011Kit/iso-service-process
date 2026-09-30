package com.isoservice.process.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "bookings")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String clientName;
    private String email;
    private Integer numberOfPax;
    private Instant appointmentDate;

    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    private String photographerId;
    private Instant statusTimestamp;
    private String statusReason;

    public enum StatusType {
        SUBMITTED, PENDING_REVIEW, APPROVED, REJECTED
    }

}
