package com.burningbros.app.features.maintenanceRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.burningbros.app.base.BaseController;
import com.burningbros.app.features.users.User;
import com.burningbros.app.features.vehicles.VehicleDTO;
import com.burningbros.app.features.vehicles.VehicleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/maintenanceRecords")
public class MaintenanceRecordController extends BaseController {

    @Autowired
    private MaintenanceRecordService maintenanceRecordService;

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<MaintenanceRecord> createMaintenanceRecord(@RequestBody MaintenanceRecord maintenanceRecord) {
        Optional<VehicleDTO> vehicle = vehicleService.getVehicleById(maintenanceRecord.getVehicleId());

        if (vehicle.isEmpty()) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        Long userId = vehicle.get().getUserId();

        if (isValidUser(userId)) {
            MaintenanceRecord createdMaintenanceRecord = maintenanceRecordService.createMaintenanceRecord(maintenanceRecord);
            return new ResponseEntity<>(createdMaintenanceRecord, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllMaintenanceRecords() {
        List<MaintenanceRecordDTO> maintenanceRecords = new ArrayList<>();

        User user = userService.getCurrentUser();

        if (user.isAdmin()) {
            maintenanceRecords = maintenanceRecordService.getAllMaintenanceRecordsByAdmin();
        } else {
            maintenanceRecords = maintenanceRecordService.getAllMaintenanceRecordsByUser(user.getId());
        }
        
        return new ResponseEntity<>(maintenanceRecords, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceRecordDTO> getMaintenanceRecordById(@PathVariable Long id) {
        Optional<MaintenanceRecordDTO> record = maintenanceRecordService.getMaintenanceRecordById(id);
        if (record.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        Long userID = record.get().getVehicle().getUserId();

        if (isValidUser(userID)) {
            return ResponseEntity.ok(record.get());
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceRecord> updateMaintenanceRecord(@PathVariable Long id, @RequestBody MaintenanceRecord maintenanceRecord) {
        Optional<MaintenanceRecordDTO> record = maintenanceRecordService.getMaintenanceRecordById(id);
        if (record.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        Long userID = record.get().getVehicle().getUserId();

        if (isValidUser(userID)) {
            MaintenanceRecord updatedMaintenanceRecord = maintenanceRecordService.updateMaintenanceRecord(id, maintenanceRecord);
            return updatedMaintenanceRecord != null ? ResponseEntity.ok(updatedMaintenanceRecord) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
}
