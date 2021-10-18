package com.equipment.crudrestful.controller;

import com.equipment.crudrestful.exceptions.ResourceNotFoundException;
import com.equipment.crudrestful.model.Equipment;
import com.equipment.crudrestful.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    // get Equipment by equipmentType
//    @GetMapping("/equipment")
    @GetMapping
    public List<Equipment> getEquipmentByEquipmentType(@RequestParam MultiValueMap<String, String> filters) {

        String[] parts = filters.toString().split(":");
        String part1 = parts[1].replace("]}", "");

        Equipment filterEquipment = new Equipment(1L, part1, "test", "test");
        List<Equipment> result = new ArrayList<>();

        List<Equipment> allEquipment = equipmentRepository.findAll();
//        Stream equipment = allEquipment.stream();
//        equipment.forEach(e -> {
//            if (e.equals(filterEquipment)) {
//                result.add(e);
//            }
//        });

        for (Equipment e : allEquipment) {
            if (e.equals(filterEquipment)) {
                result.add(e);
            }
        }
        return result;
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
