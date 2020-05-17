package ua.nure.akrpz.iot.dao;

import ua.nure.akrpz.iot.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDao extends JpaRepository<Sensor, Long> {
    // TODO check if it is still needed
    // Set<Sensor> findByBuilding(Building building);
}
