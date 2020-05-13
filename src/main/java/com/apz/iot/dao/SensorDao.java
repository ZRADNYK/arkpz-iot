package com.apz.iot.dao;

import com.apz.iot.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDao extends JpaRepository<Sensor, Long> {
    // TODO check if it is still needed
    // Set<Sensor> findByBuilding(Building building);
}
