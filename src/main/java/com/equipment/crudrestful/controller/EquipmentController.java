package com.equipment.crudrestful.controller;

import com.equipment.crudrestful.exceptions.ResourceNotFoundException;
import com.equipment.crudrestful.model.Equipment;
import com.equipment.crudrestful.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentRepository equipmentRepository;

    // get all Equipment
    @GetMapping("/equipment")
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    // create Equipment
    @PostMapping("/equipment")
    public Equipment createEquipment(@Valid @RequestBody Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    // get Equipment by id
    @GetMapping("equipment/{equipmentNumber}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable(value = "equipmentNumber") long equipmentNumber)
            throws ResourceNotFoundException {
        Equipment equipment = equipmentRepository.findById(equipmentNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not found for this id :: " + equipmentNumber));
        return ResponseEntity.ok().body(equipment);
    }

    // update Equipment
    @PutMapping("/equipment/{equipmentNumber}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable(value = "equipmentNumber") long equipmentNumber, @RequestBody Equipment equipmentDetails)
            throws ResourceNotFoundException {
        Equipment equipment = equipmentRepository.findById(equipmentNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not found for this id :: " + equipmentNumber));
        equipment.setEquipmentType(equipmentDetails.getEquipmentType());
        equipment.setStatus(equipmentDetails.getStatus());
        equipment.setDeviceId(equipmentDetails.getDeviceId());
        equipmentRepository.save(equipment);
        return ResponseEntity.ok().body(equipment);
    }

    // delete equipment by id
    @DeleteMapping("/equipment/{equipmentNumber}")
    public ResponseEntity<?> deleteEquipment(@PathVariable(value = "equipmentNumber") long equipmentNumber) throws ResourceNotFoundException {
        equipmentRepository.findById(equipmentNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not found for this id :: " + equipmentNumber));

        equipmentRepository.deleteById(equipmentNumber);
        return ResponseEntity.ok().build();
    }
}
