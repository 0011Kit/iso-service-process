package com.isoservice.process.domain;

import java.time.Instant;

public sealed interface BookingStatus
    permits BookingStatus.Submitted, BookingStatus.PendingReview, BookingStatus.Approved, BookingStatus.Rejected {

        record Submitted() implements BookingStatus{}

        record PendingReview (String photographerId, Instant inquiredAt) implements BookingStatus{}

        record Approved (String photographerId, Instant inquiredAt) implements BookingStatus{}

        record Rejected (String photographerId, Instant inquiredAt, String reason) implements BookingStatus{}

}
