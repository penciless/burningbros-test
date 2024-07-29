package com.burningbros.app.features.maintenanceRecords;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecord, Long> {
    String QUERY_GET_ALL_RECORDS_AS_ADMIN = "SELECT mr.id AS id, mr.date, mr.description, mr.cost, u.id as userId, v.id AS vehicleId, v.brand, v.model, v.year, v.type FROM accounts u JOIN vehicles v ON u.id = v.user_id JOIN maintenance_records mr ON v.id = mr.vehicle_id";
    String QUERY_GET_ALL_RECORDS_AS_USER = QUERY_GET_ALL_RECORDS_AS_ADMIN + " WHERE u.id = :userId";
    String QUERY_GET_FULL_RECORD_BY_ID = QUERY_GET_ALL_RECORDS_AS_ADMIN + " WHERE mr.id = :recordId";
    
    @Query(value = QUERY_GET_ALL_RECORDS_AS_ADMIN, nativeQuery = true)
    List<Map<String, Object>> getAllMaintenanceRecordsByAdmin();

    @Query(value = QUERY_GET_ALL_RECORDS_AS_USER, nativeQuery = true)
    List<Map<String, Object>> getAllMaintenanceRecordsByUser(@Param("userId") Long userId);

    @Query(value = QUERY_GET_FULL_RECORD_BY_ID, nativeQuery = true)
    List<Map<String, Object>> getFullRecordById(@Param("recordId") Long recordId);
}
