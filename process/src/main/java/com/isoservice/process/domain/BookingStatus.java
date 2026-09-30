package com.isoservice.process.domain;

public sealed interface BookingStatus
permits Submitted, PendingReview, Approved, Rejected{
    record Submitted() implements BookingStatus{}

    record PendingReview (String photographerId, Instant inquiredAt) implements BookingStatus{}

    record Approved (String photographerId, Instant inquiredAt) implements BookingStatus{}

    record Rejected (String photographerId, Instant inquiredAt, String reason) implements BookingStatus{}

}
