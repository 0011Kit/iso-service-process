package com.isoservice.process.repository;

import com.isoservice.process.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<StaffEntity, String> {
    List<StaffEntity> findAllByStaffName(String name);
}
