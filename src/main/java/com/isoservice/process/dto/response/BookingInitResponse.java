package com.isoservice.process.dto.response;

import java.time.Instant;
import java.util.UUID;

public record BookingInitResponse(
        String clientName,
        String email,
        Instant appointmentDate,
        Integer numberOfPax,
        String status
){}
