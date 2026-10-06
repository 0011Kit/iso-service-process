package com.isoservice.process.dto.request;

import java.time.Instant;

public record BookingInitRequest (
    String clientName,
    String email,
    Integer numberOfPax,
    Instant appointmentDate,
    String photographerName
    ){}