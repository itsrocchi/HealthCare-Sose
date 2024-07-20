package it.univaq.sose.healthcareManagementService.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/hms/aggregatedData")
@EnableAsync
public class AggregateController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Async
    public CompletableFuture<ResponseEntity<String>> getPatientData(String cf) {
        String url = "http://localhost:8080/prs/patientData/" + cf;
        return CompletableFuture.supplyAsync(() -> restTemplate.getForEntity(url, String.class));
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> getMedicalRecord(String cf) {
        String url = "http://localhost:8081/mhs/medicalRecord/" + cf;
        return CompletableFuture.supplyAsync(() -> restTemplate.getForEntity(url, String.class));
    }

    @GetMapping("/{cf}")
    public CompletableFuture<ResponseEntity<String>> aggregatePatientData(@PathVariable String cf) {
        CompletableFuture<ResponseEntity<String>> patientDataFuture = getPatientData(cf);
        CompletableFuture<ResponseEntity<String>> medicalRecordDataFuture = getMedicalRecord(cf);

        return patientDataFuture.thenCombine(medicalRecordDataFuture, (patientResponse, medicalResponse) -> {
            if (patientResponse.getStatusCode() == HttpStatus.OK && medicalResponse.getStatusCode() == HttpStatus.OK) {
                try {
                    JsonNode patientDataNode = objectMapper.readTree(patientResponse.getBody());
                    JsonNode medicalRecordDataNode = objectMapper.readTree(medicalResponse.getBody());

                    ((ObjectNode) patientDataNode).remove("cf");
                    ((ObjectNode) medicalRecordDataNode).remove("cf");

                    ObjectNode mergedResponseNode = objectMapper.createObjectNode();
                    mergedResponseNode.put("cf", cf);
                    mergedResponseNode.set("patientData", patientDataNode);
                    mergedResponseNode.set("medicalRecordData", medicalRecordDataNode);

                    String mergedResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedResponseNode);

                    return new ResponseEntity<>(mergedResponse, HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>("An error occurred while processing the data", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>("Failed to fetch data from one or both services", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        });
    }
}
