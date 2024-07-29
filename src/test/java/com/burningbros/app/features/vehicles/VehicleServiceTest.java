package com.burningbros.app.features.vehicles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleQueryParser vehicleQueryParser;

    @Test
    public void testCreateVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setUserId(1L);
        vehicle.setBrand("Toyota");
        vehicle.setModel("T1000");
        vehicle.setYear(2000);
        vehicle.setType("car");

        Mockito.when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        Vehicle created = vehicleService.createVehicle(vehicle);

        assertEquals(2000, created.getYear());
    }

    @Test
    public void testGetVehicleById() {
        List<Map<String,Object>> emptyList = new ArrayList<>();
        List<VehicleDTO> emptyVehicles = new ArrayList<>();
        
        Mockito.when(vehicleRepository.getVehicleById(1L)).thenReturn(emptyList);
        Mockito.when(vehicleQueryParser.parseVehicles(emptyList)).thenReturn(emptyVehicles);

        Optional<VehicleDTO> vehicle = vehicleService.getVehicleById(1L);

        assertEquals(true, vehicle.isEmpty());
    }

    @Test
    public void testGetVehiclesByAdmin() {
        List<Map<String,Object>> emptyList = new ArrayList<>();
        List<VehicleDTO> emptyVehicles = new ArrayList<>();
        
        Mockito.when(vehicleRepository.getAllVehiclesByAdmin()).thenReturn(emptyList);
        Mockito.when(vehicleQueryParser.parseVehicles(emptyList)).thenReturn(emptyVehicles);

        List<VehicleDTO> vehicles = vehicleService.getVehiclesByAdmin();

        assertEquals(true, vehicles.isEmpty());
    }

    @Test
    public void testGetVehiclesByUser() {
        List<Map<String,Object>> emptyList = new ArrayList<>();
        List<VehicleDTO> emptyVehicles = new ArrayList<>();
        
        Mockito.when(vehicleRepository.getAllVehiclesByUser(1L)).thenReturn(emptyList);
        Mockito.when(vehicleQueryParser.parseVehicles(emptyList)).thenReturn(emptyVehicles);

        List<VehicleDTO> vehicles = vehicleService.getVehiclesByUser(1L);

        assertEquals(true, vehicles.isEmpty());
    }

    @Test
    public void testUpdateVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setUserId(1L);
        vehicle.setBrand("Toyota");
        vehicle.setModel("T1000");
        vehicle.setYear(2000);
        vehicle.setType("car");
        
        Mockito.when(vehicleRepository.existsById(1L)).thenReturn(true);
        Mockito.when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        Vehicle updated = vehicleService.updateVehicle(1L, vehicle);

        assertEquals(updated.getBrand(), vehicle.getBrand());
    }
}
