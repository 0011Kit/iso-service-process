package com.isoservice.process.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "staff")
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "staff_name")
    private String staffName;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "employment_category")
    @Enumerated(EnumType.STRING)
    private EmploymentCategory employmentCategory;

    @Column(name = "employment_status")
    @Enumerated(EnumType.STRING)
    private EmploymentStatusType employmentStatus;

    @Column(name = "employment_start_date")
    private Instant employmentStartDate;

    public enum EmploymentStatusType {
        ACTIVE, UNEMPLOYED, ONLEAVE
    }

    public enum EmploymentCategory {
        PHOTOGRAPHER, ADMIN, PARTTIME
    }

    public enum Gender {
        FEMALE, MALE
    }

    public UUID getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmploymentCategory getEmploymentCategory() {
        return employmentCategory;
    }

    public void setEmploymentCategory(EmploymentCategory employmentCategory) {
        this.employmentCategory = employmentCategory;
    }

    public EmploymentStatusType getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatusType employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public Instant getEmploymentStartDate() {
        return employmentStartDate;
    }

    public void setEmploymentStartDate(Instant employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }
}