package ua.nure.akrpz.iot.service;

import ua.nure.akrpz.iot.dao.BuildingDao;
import ua.nure.akrpz.iot.dao.SensorDao;
import ua.nure.akrpz.iot.model.Building;
import ua.nure.akrpz.iot.model.Customer;
import ua.nure.akrpz.iot.model.Sensor;
import ua.nure.akrpz.iot.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class EntryService {
    private final BuildingDao buildingDao;
    private final SensorDao sensorDao;
    public ArrayList<Sensor> triggeredSensors = new ArrayList<>();


    @Autowired
    public EntryService(BuildingDao buildingDao, SensorDao sensorDao) {
        this.buildingDao = buildingDao;
        this.sensorDao = sensorDao;
    }

    @Scheduled(fixedDelay = 15000)
    void clean() {
        triggeredSensors = new ArrayList<>();
    }

    public Customer emulateCustomerEnter() {
        ArrayList<Sensor> sensors = (ArrayList<Sensor>) sensorDao.findAll();
        Building building = findRandomBuilding();

        triggerMovementSensors(sensors, triggeredSensors, "FIRST", "SECOND");

        return Customer.builder()
                .temperature(emulateTemperatureMeasuring())
                .buildingId(building.getBuildingId())
                .build();
    }

    public Customer emulateCustomerExit() {
        ArrayList<Sensor> sensors = (ArrayList<Sensor>) sensorDao.findAll();
        Building building = findRandomBuilding();

        triggerMovementSensors(sensors, triggeredSensors, "SECOND", "FIRST");

        return Customer.builder()
                .temperature(emulateTemperatureMeasuring())
                .buildingId(building.getBuildingId())
                .build();
    }

    public Worker emulateWorkerEnter() {
        ArrayList<Sensor> sensors = (ArrayList<Sensor>) sensorDao.findAll();
        Building building = findRandomBuilding();
        triggerMovementSensors(sensors, triggeredSensors, "FIRST", "SECOND");

        return Worker.builder()
                .temperature(emulateTemperatureMeasuring())
                .buildingId(building.getBuildingId())
                .build();
    }

    public Worker emulateWorkerExit() {
        ArrayList<Sensor> sensors = (ArrayList<Sensor>) sensorDao.findAll();
        Building building = findRandomBuilding();

        triggerMovementSensors(sensors, triggeredSensors, "SECOND", "FIRST");

        return Worker.builder()
                .temperature(emulateTemperatureMeasuring())
                .buildingId(building.getBuildingId())
                .build();
    }

    private void triggerMovementSensors(ArrayList<Sensor> sensors, ArrayList<Sensor> triggeredSensors,
                                        String sensorPositionOne, String sensorPositionTwo) {
        for (int i = 0; i < sensors.size(); i++) {
            if (sensors.get(i).getSensorPosition().equals(sensorPositionOne)) {
                triggeredSensors.add(sensors.get(i));
                for (Sensor sensor : sensors) {
                    if (sensor.getSensorPosition().equals(sensorPositionTwo)) {
                        triggeredSensors.add(sensor);
                    }
                }
            }
        }
    }

    private double emulateTemperatureMeasuring() {
        double start = 36.4;
        double end = 39.0;
        double random = new Random().nextDouble();
        return start + (random * (end - start));
    }

    private Building findRandomBuilding() {
        ArrayList<Building> buildings = (ArrayList<Building>) buildingDao.findAll();
        return buildings.get(new Random().nextInt(buildings.size()));
    }
}
