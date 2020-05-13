package com.apz.iot.dao;

import com.apz.iot.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingDao extends JpaRepository<Building, Long> {
    Building findByNameAndAddress(String name, String address);

    Building findByAddress(String address);

    Building findByName(String buildingName);
}
