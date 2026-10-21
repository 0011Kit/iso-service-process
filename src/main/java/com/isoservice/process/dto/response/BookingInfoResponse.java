package com.isoservice.process.dto.response;

import java.time.Instant;
import java.util.UUID;

public record BookingInfoResponse(
        UUID id,
        String clientName,
        String email,
        Integer numberOfPax,
        Instant appointmentDate,
        String status,
        UUID photographerId,
        String statusTimestamp,
        String statusReason
) {}
