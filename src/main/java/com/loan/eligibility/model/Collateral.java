package com.loan.eligibility.model;

import javax.persistence.*;

@Entity
@Table(name = "collateral")
public class Collateral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collateral_id")
    private Integer collateralId;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "vehicle_make", nullable = false)
    private String vehicleMake;

    @Column(name = "vehicle_model", nullable = false)
    private String vehicleModel;

    @Column(name = "vehicle_year", nullable = false)
    private Integer vehicleYear;

    @Column(name = "vehicle_value", nullable = false)
    private Double vehicleValue;

    @Column(name = "appraisal_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date appraisalDate;

    // Getters and Setters

    public Integer getCollateralId() {
        return collateralId;
    }

    public void setCollateralId(Integer collateralId) {
        this.collateralId = collateralId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Integer getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(Integer vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public Double getVehicleValue() {
        return vehicleValue;
    }

    public void setVehicleValue(Double vehicleValue) {
        this.vehicleValue = vehicleValue;
    }

    public java.util.Date getAppraisalDate() {
        return appraisalDate;
    }

    public void setAppraisalDate(java.util.Date appraisalDate) {
        this.appraisalDate = appraisalDate;
    }
}
