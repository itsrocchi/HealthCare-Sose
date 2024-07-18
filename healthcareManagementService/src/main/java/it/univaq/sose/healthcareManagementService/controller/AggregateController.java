package it.univaq.sose.healthcareManagementService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/hms/aggregatedData")
public class AggregateController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{cf}")
    public ResponseEntity<String> aggregatePatientData(@PathVariable String cf) {
        try {
            // Get patient data from Patient Record Service
            String getPatientDataUrl = "http://localhost:8080/prs/patientData/" + cf;
            ResponseEntity<String> getResponse = restTemplate.getForEntity(getPatientDataUrl, String.class);

            // Get medical record data from Medical History Service
            String checkMedicalRecordUrl = "http://localhost:8081/mhs/medicalRecord/" + cf;
            ResponseEntity<String> checkResponse = restTemplate.getForEntity(checkMedicalRecordUrl, String.class);

            // Check if both responses are successful
            if (getResponse.getStatusCode() == HttpStatus.OK && checkResponse.getStatusCode() == HttpStatus.OK) {
                // Parse the responses into JsonNode objects
                // Parse the responses into JsonNode objects
                JsonNode patientDataNode = objectMapper.readTree(getResponse.getBody());
                JsonNode medicalRecordDataNode = objectMapper.readTree(checkResponse.getBody());

                // Remove the 'cf' field from patientData and medicalRecordData
                ((ObjectNode) patientDataNode).remove("cf");
                ((ObjectNode) medicalRecordDataNode).remove("cf");

                // Create a new JSON object and add the parsed responses
                ObjectNode mergedResponseNode = objectMapper.createObjectNode();
                mergedResponseNode.put("cf", cf);
                mergedResponseNode.set("patientData", patientDataNode);
                mergedResponseNode.set("medicalRecordData", medicalRecordDataNode);

                // Convert the merged JSON object to a pretty-printed string
                String mergedResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedResponseNode);

                // Return the aggregated data
                return new ResponseEntity<>(mergedResponse, HttpStatus.OK);
            } else {
                // Handle cases where one or both services failed
                return new ResponseEntity<>("Failed to fetch data from one or both services", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (JsonProcessingException e) {
            // Handle JSON processing exceptions
            return new ResponseEntity<>("An error occurred while processing the data", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // Handle other exceptions
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
