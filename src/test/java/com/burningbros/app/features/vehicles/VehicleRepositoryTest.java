package com.burningbros.app.features.vehicles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void testGetAllVehiclesByAdmin() {
        List<Map<String, Object>> vehicles = vehicleRepository.getAllVehiclesByAdmin();
        assertNotNull(vehicles);
        assertEquals(true, vehicles.isEmpty());
    }

    @Test
    public void testGetAllVehiclesByUser() {
        List<Map<String, Object>> vehicles = vehicleRepository.getAllVehiclesByUser(1L);
        assertNotNull(vehicles);
        assertEquals(true, vehicles.isEmpty());
    }

    @Test
    public void testGetVehicleById() {
        List<Map<String, Object>> vehicles = vehicleRepository.getVehicleById(1L);
        assertNotNull(vehicles);
        assertEquals(true, vehicles.isEmpty());
    }
}

