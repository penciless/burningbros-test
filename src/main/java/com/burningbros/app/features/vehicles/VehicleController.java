package com.burningbros.app.features.vehicles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.burningbros.app.base.BaseController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController extends BaseController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        if (isAdmin()) {
            Vehicle createdVehicle = vehicleService.createVehicle(vehicle);
            return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> vehicles = new ArrayList<>();
        
        if (isAdmin()) {
            vehicles = vehicleService.getVehiclesByAdmin();
        } else {
            vehicles = vehicleService.getVehiclesByUser(userService.getCurrentUser().getId());
        }

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        Optional<VehicleDTO> vehicle = vehicleService.getVehicleById(id);
        if (vehicle.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        Long userID = vehicle.get().getUserId();

        if (isValidUser(userID)) {
            return ResponseEntity.ok(vehicle.get());
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle updateVehicle) {
        Optional<VehicleDTO> targetVehicle = vehicleService.getVehicleById(id);
        if (targetVehicle.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        Long userId = targetVehicle.get().getUserId();

        if (isValidUser(userId)) {
            Vehicle changedVehicle = vehicleService.updateVehicle(id, updateVehicle);
            return changedVehicle != null ? ResponseEntity.ok(changedVehicle) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
}
