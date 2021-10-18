package com.equipment.crudrestful.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long equipmentNumber;

    @Column(name = "equipmentType")
    private String equipmentType;

    @Column(name = "status")
    private String status;

    @Column(name = "deviceId")
    private String deviceId;


    public Equipment(Long o, String part, String status, String deviceId) {
    }

    public Equipment(long equipmentNumber, String equipmentType, String status, String deviceId) {
        this.equipmentNumber = equipmentNumber;
        this.equipmentType = equipmentType;
        this.status = status;
        this.deviceId = deviceId;
    }

    public Equipment() {

    }

    public long getEquipmentNumber() {
        return equipmentNumber;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return equipmentType.equals(equipment.equipmentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipmentType);
    }
}
