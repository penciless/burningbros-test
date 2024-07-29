package com.burningbros.app.features.vehicles;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    String QUERY_ALL_VEHICLES_BY_ADMIN = "SELECT v.id AS vehicleId, v.brand, v.model, v.year, v.type, u.id AS userId, mr.id AS recordId, mr.date, mr.description, mr.cost FROM accounts u JOIN vehicles v ON u.id = v.user_id LEFT JOIN maintenance_records mr ON v.id = mr.vehicle_id";
    String QUERY_ALL_VEHICLES_BY_USER = QUERY_ALL_VEHICLES_BY_ADMIN + " WHERE u.id = :userId";
    String QUERY_VEHICLE_BY_ID = QUERY_ALL_VEHICLES_BY_ADMIN + " WHERE v.id = :vehicleId";

    @Query(value = QUERY_ALL_VEHICLES_BY_ADMIN, nativeQuery = true)
    List<Map<String, Object>> getAllVehiclesByAdmin();

    @Query(value = QUERY_ALL_VEHICLES_BY_USER, nativeQuery = true)
    List<Map<String, Object>> getAllVehiclesByUser(@Param("userId") Long userId);

    @Query(value = QUERY_VEHICLE_BY_ID, nativeQuery = true)
    List<Map<String, Object>> getVehicleById(@Param("vehicleId") Long vehicleId);
}
