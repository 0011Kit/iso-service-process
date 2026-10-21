package com.isoservice.process.domain;

import java.time.Instant;
import java.util.UUID;

public sealed interface BookingStatus
    permits BookingStatus.Submitted, BookingStatus.PendingReview, BookingStatus.Approved, BookingStatus.Rejected {

        record Submitted() implements BookingStatus{}

        record PendingReview (UUID photographerId, Instant inquiredAt) implements BookingStatus{}

        record Approved (UUID photographerId, Instant inquiredAt) implements BookingStatus{}

        record Rejected (UUID photographerId, Instant inquiredAt, String reason) implements BookingStatus{}

}
