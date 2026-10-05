package com.isoservice.process.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "bookings")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "client_name")
    private String clientName;
    private String email;

    @Column(name = "number_of_pax")
    private Integer numberOfPax;
    @Column(name = "appointment_date")
    private Instant appointmentDate;


    @Column(name = "status_type")
    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    @Column(name = "photographer_id")
    private String photographerId;

    @Column(name = "status_timestamp")
    private Instant statusTimestamp;

    @Column(name = "status_reason")
    private String statusReason;

    public enum StatusType {
        SUBMITTED, PENDING_REVIEW, APPROVED, REJECTED
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumberOfPax() {
        return numberOfPax;
    }

    public void setNumberOfPax(Integer numberOfPax) {
        this.numberOfPax = numberOfPax;
    }

    public Instant getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Instant appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public String getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(String photographerId) {
        this.photographerId = photographerId;
    }

    public Instant getStatusTimestamp() {
        return statusTimestamp;
    }

    public void setStatusTimestamp(Instant statusTimestamp) {
        this.statusTimestamp = statusTimestamp;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }
}
