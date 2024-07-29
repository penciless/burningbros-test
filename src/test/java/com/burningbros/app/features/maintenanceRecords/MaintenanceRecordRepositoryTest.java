package com.burningbros.app.features.maintenanceRecords;

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
public class MaintenanceRecordRepositoryTest {

    @Autowired
    private MaintenanceRecordRepository maintenanceRecordRepository;

    @Test
    public void testGetAllMaintenanceRecordsByAdmin() {
        List<Map<String, Object>> records = maintenanceRecordRepository.getAllMaintenanceRecordsByAdmin();
        assertNotNull(records);
        assertEquals(true, records.isEmpty());
    }

    @Test
    public void testGetAllMaintenanceRecordsByUser() {
        List<Map<String, Object>> records = maintenanceRecordRepository.getAllMaintenanceRecordsByUser(1L);
        assertNotNull(records);
        assertEquals(true, records.isEmpty());
    }

    @Test
    public void testGetFullRecordById() {
        List<Map<String, Object>> records = maintenanceRecordRepository.getFullRecordById(1L);
        assertNotNull(records);
        assertEquals(true, records.isEmpty());
    }
}

