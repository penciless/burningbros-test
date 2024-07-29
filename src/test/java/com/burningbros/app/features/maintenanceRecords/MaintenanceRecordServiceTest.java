package com.burningbros.app.features.maintenanceRecords;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
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
public class MaintenanceRecordServiceTest {

    @InjectMocks
    private MaintenanceRecordService maintenanceRecordService;

    @Mock
    private MaintenanceRecordRepository maintenanceRecordRepository;

    @Mock
    private MaintenanceRecordQueryParser maintenanceRecordQueryParser;

    @Test
    public void testCreateMaintenanceRecord() {
        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
        maintenanceRecord.setId(1L);
        maintenanceRecord.setVehicleId(1L);
        maintenanceRecord.setDescription("Fix issues");
        maintenanceRecord.setDate(new Date());
        maintenanceRecord.setCost(20.6);

        Mockito.when(maintenanceRecordRepository.save(maintenanceRecord)).thenReturn(maintenanceRecord);

        MaintenanceRecord created = maintenanceRecordService.createMaintenanceRecord(maintenanceRecord);

        assertEquals(20.6, created.getCost());
    }

    @Test
    public void testGetMaintenanceRecordById() {
        List<Map<String,Object>> emptyList = new ArrayList<>();
        List<MaintenanceRecordDTO> emptyMaintenanceRecords = new ArrayList<>();
        
        Mockito.when(maintenanceRecordRepository.getFullRecordById(1L)).thenReturn(emptyList);
        Mockito.when(maintenanceRecordQueryParser.parseMaintenanceRecords(emptyList)).thenReturn(emptyMaintenanceRecords);

        Optional<MaintenanceRecordDTO> maintenanceRecord = maintenanceRecordService.getMaintenanceRecordById(1L);

        assertEquals(true, maintenanceRecord.isEmpty());
    }

    @Test
    public void testGetMaintenanceRecordsByAdmin() {
        List<Map<String,Object>> emptyList = new ArrayList<>();
        List<MaintenanceRecordDTO> emptyMaintenanceRecords = new ArrayList<>();
        
        Mockito.when(maintenanceRecordRepository.getAllMaintenanceRecordsByAdmin()).thenReturn(emptyList);
        Mockito.when(maintenanceRecordQueryParser.parseMaintenanceRecords(emptyList)).thenReturn(emptyMaintenanceRecords);

        List<MaintenanceRecordDTO> maintenanceRecords = maintenanceRecordService.getAllMaintenanceRecordsByAdmin();

        assertEquals(true, maintenanceRecords.isEmpty());
    }

    @Test
    public void testGetMaintenanceRecordsByUser() {
        List<Map<String,Object>> emptyList = new ArrayList<>();
        List<MaintenanceRecordDTO> emptyMaintenanceRecords = new ArrayList<>();
        
        Mockito.when(maintenanceRecordRepository.getAllMaintenanceRecordsByUser(1L)).thenReturn(emptyList);
        Mockito.when(maintenanceRecordQueryParser.parseMaintenanceRecords(emptyList)).thenReturn(emptyMaintenanceRecords);

        List<MaintenanceRecordDTO> maintenanceRecords = maintenanceRecordService.getAllMaintenanceRecordsByUser(1L);

        assertEquals(true, maintenanceRecords.isEmpty());
    }

    @Test
    public void testUpdateMaintenanceRecord() {
        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
        maintenanceRecord.setId(1L);
        maintenanceRecord.setVehicleId(1L);
        maintenanceRecord.setDescription("Fix issues");
        maintenanceRecord.setDate(new Date());
        maintenanceRecord.setCost(20.6);
        
        Mockito.when(maintenanceRecordRepository.existsById(1L)).thenReturn(true);
        Mockito.when(maintenanceRecordRepository.save(maintenanceRecord)).thenReturn(maintenanceRecord);

        MaintenanceRecord updated = maintenanceRecordService.updateMaintenanceRecord(1L, maintenanceRecord);

        assertEquals(updated.getDescription(), maintenanceRecord.getDescription());
    }
}
