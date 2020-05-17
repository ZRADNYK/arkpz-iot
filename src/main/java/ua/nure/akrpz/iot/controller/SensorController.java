package ua.nure.akrpz.iot.controller;

import org.springframework.beans.factory.annotation.Value;
import ua.nure.akrpz.iot.model.Customer;
import ua.nure.akrpz.iot.model.Worker;
import ua.nure.akrpz.iot.service.EntryService;
import ua.nure.akrpz.iot.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class SensorController {
    private final RestTemplate restTemplate;
    private final EntryService entryService;

    @Value("${backend-url}")
    private String backendUrl;

    @Autowired
    public SensorController(RestTemplate restTemplate, EntryService entryService) {
        this.restTemplate = restTemplate;
        this.entryService = entryService;
    }

    @Scheduled(fixedDelay = 1000)
    public void customerEnteredBuilding() {
        Customer customer = entryService.emulateCustomerEnter();
        HttpEntity<String> entity = RestTemplateUtil.createHttpEntity(customer);

        restTemplate.exchange(backendUrl + "/customers/enter", HttpMethod.POST, entity, ArrayList.class);
    }

    @Scheduled(fixedDelay = 4000)
    public void customerLeftBuilding() {
        Customer customer = entryService.emulateCustomerExit();
        HttpEntity<String> entity = RestTemplateUtil.createHttpEntity(customer);

        restTemplate.exchange(backendUrl + "/customers/left", HttpMethod.POST, entity, HttpStatus.class);
    }

    @Scheduled(fixedDelay = 5000)
    public void workerEnteredBuilding() {
        Worker worker = entryService.emulateWorkerEnter();
        HttpEntity<String> entity = RestTemplateUtil.createHttpEntity(worker);

        restTemplate.exchange(backendUrl + "/workers/enter", HttpMethod.POST, entity, ArrayList.class);
    }

    @Scheduled(fixedDelay = 10000)
    public void workerLeftBuilding() {
        Worker worker = entryService.emulateWorkerExit();
        HttpEntity<String> entity = RestTemplateUtil.createHttpEntity(worker);

        restTemplate.exchange(backendUrl + "/workers/left", HttpMethod.POST, entity, ArrayList.class);
    }
}
