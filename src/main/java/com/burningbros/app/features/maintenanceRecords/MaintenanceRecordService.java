package com.burningbros.app.features.maintenanceRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MaintenanceRecordService {

    @Autowired
    private MaintenanceRecordRepository maintenanceRecordRepository;

    @Autowired
    private MaintenanceRecordQueryParser maintenanceRecordQueryParser;

    public MaintenanceRecord createMaintenanceRecord(MaintenanceRecord maintenanceRecord) {
        maintenanceRecordRepository.save(maintenanceRecord);
        return maintenanceRecord;
    }

    public List<MaintenanceRecordDTO> getAllMaintenanceRecordsByAdmin() {
        List<Map<String, Object>> results = maintenanceRecordRepository.getAllMaintenanceRecordsByAdmin();
        List<MaintenanceRecordDTO> records = maintenanceRecordQueryParser.parseMaintenanceRecords(results);
        return records;
    }

    public List<MaintenanceRecordDTO> getAllMaintenanceRecordsByUser(Long userId) {
        List<Map<String, Object>> results = maintenanceRecordRepository.getAllMaintenanceRecordsByUser(userId);
        List<MaintenanceRecordDTO> records = maintenanceRecordQueryParser.parseMaintenanceRecords(results);
        return records;
    }

    public Optional<MaintenanceRecordDTO> getMaintenanceRecordById(Long recordId) {
        List<Map<String, Object>> results = maintenanceRecordRepository.getFullRecordById(recordId);
        List<MaintenanceRecordDTO> records = maintenanceRecordQueryParser.parseMaintenanceRecords(results);
        return Optional.ofNullable(records.size() > 0 ? records.get(0) : null);
    }

    public MaintenanceRecord updateMaintenanceRecord(Long id, MaintenanceRecord maintenanceRecord) {
        if (maintenanceRecordRepository.existsById(id)) {
            maintenanceRecord.setId(id);
            return maintenanceRecordRepository.save(maintenanceRecord);
        }
        return null;
    }
}
