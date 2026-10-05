package com.isoservice.process.mapper;

import com.isoservice.process.domain.BookingStatus;
import com.isoservice.process.entity.BookingEntity;

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
                entity.setStatusType(BookingEntity.StatusType.SUBMITTED);
            }
            case BookingStatus.PendingReview(var photographerId, var inquiredAt) -> {
                entity.setStatusType(BookingEntity.StatusType.PENDING_REVIEW);
                entity.setPhotographerId(photographerId);
                entity.setStatusTimestamp(inquiredAt);
            }
            case BookingStatus.Approved(var photographerId, var approvedAt) -> {
                entity.setStatusType(BookingEntity.StatusType.APPROVED);
                entity.setPhotographerId(photographerId);
                entity.setStatusTimestamp(approvedAt);
            }
            case BookingStatus.Rejected(var photographerId, var rejectedAt, var reason) -> {
                entity.setStatusType(BookingEntity.StatusType.REJECTED);
                entity.setPhotographerId(photographerId);
                entity.setStatusTimestamp(rejectedAt);
                entity.setStatusReason(reason);
            }
        }
    }


}
