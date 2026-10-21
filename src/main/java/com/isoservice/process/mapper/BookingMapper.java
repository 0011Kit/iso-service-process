package com.isoservice.process.mapper;

import com.isoservice.process.domain.BookingStatus;
import com.isoservice.process.dto.response.BookingInfoResponse;
import com.isoservice.process.dto.response.BookingInitResponse;
import com.isoservice.process.entity.BookingEntity;

import java.util.List;

public class BookingMapper {

    public static BookingStatus toDomainStatus (BookingEntity entity){
        return switch (entity.getStatusType()){
            case SUBMITTED -> new BookingStatus.Submitted();
            case PENDING_REVIEW -> new BookingStatus.PendingReview(
                    entity.getPhotographerId(), entity.getStatusTimestamp());
            case APPROVED -> new BookingStatus.Approved(
                    entity.getPhotographerId(), entity.getStatusTimestamp());
            case REJECTED -> new BookingStatus.Rejected(
                    entity.getPhotographerId(), entity.getStatusTimestamp(), entity.getStatusReason());
        };
    }

    public static void applyDomainStatus(BookingEntity entity, BookingStatus status) {
        switch (status) {
            case BookingStatus.Submitted s -> {
                entity.setStatusType(BookingEntity.BookingStatusType.SUBMITTED);
            }
            case BookingStatus.PendingReview(var photographerId, var inquiredAt) -> {
                entity.setStatusType(BookingEntity.BookingStatusType.PENDING_REVIEW);
                entity.setPhotographerId(photographerId);
                entity.setStatusTimestamp(inquiredAt);
            }
            case BookingStatus.Approved(var photographerId, var approvedAt) -> {
                entity.setStatusType(BookingEntity.BookingStatusType.APPROVED);
                entity.setPhotographerId(photographerId);
                entity.setStatusTimestamp(approvedAt);
            }
            case BookingStatus.Rejected(var photographerId, var rejectedAt, var reason) -> {
                entity.setStatusType(BookingEntity.BookingStatusType.REJECTED);
                entity.setPhotographerId(photographerId);
                entity.setStatusTimestamp(rejectedAt);
                entity.setStatusReason(reason);
            }
        }
    }

    public static BookingInitResponse toBookingInitResponse (BookingEntity entity){
        return new BookingInitResponse(
                entity.getClientName(),
                entity.getEmail(),
                entity.getAppointmentDate(),
                entity.getNumberOfPax(),
                entity.getStatusType().name()
        );
    }

    public static BookingInfoResponse toBookingInfoResponse (BookingEntity entity){
        return new BookingInfoResponse(
          entity.getId(),
          entity.getClientName(),
          entity.getEmail(),
          entity.getNumberOfPax(),
          entity.getAppointmentDate(),
          entity.getStatusType().name(),
          entity.getPhotographerId(),
          entity.getStatusReason(),
          entity.getStatusReason()
        );
    }

    public static List<BookingInfoResponse> toBookingInfoResponseList (List<BookingEntity> entityList){
        return entityList.stream()
                .map(BookingMapper::toBookingInfoResponse)
                .toList();
    }

}
