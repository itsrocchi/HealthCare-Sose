package it.univaq.sose.healthcareManagementService.service;

import it.univaq.sose.healthcareManagementService.exception.PatientNotFoundException;
import it.univaq.sose.healthcareManagementService.util.PatientData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

@Service
public class InsertService {

    @Autowired
    private RestTemplate restTemplate;

    public boolean processPatientData(PatientData patientData) throws PatientNotFoundException {
        String cf = patientData.getCf();
        String getPatientDataUrl = "http://localhost:8080/prs/patientData/" + cf;
        String checkMedicalRecordUrl = "http://localhost:8081/mhs/medicalRecord/" + cf;

        try {
            // Check if patient exists in Patient Record Service
            ResponseEntity<String> getResponse = restTemplate.getForEntity(getPatientDataUrl, String.class);

            if (getResponse.getStatusCode().is2xxSuccessful()) {
                // Check if the patient data already exists in Medical History Service
                try {
                    ResponseEntity<String> checkResponse = restTemplate.getForEntity(checkMedicalRecordUrl, String.class);
                    if (checkResponse.getStatusCode().is2xxSuccessful()) {
                        // If the data already exists, return false
                        return false;
                    }
                } catch (HttpClientErrorException e) {
                    if (e.getStatusCode() != HttpStatus.NOT_FOUND) {
                        // Log the detailed error message if it's not a 404 error
                        System.err.println("HTTP Client Error during GET request to " + checkMedicalRecordUrl + ": " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
                    }
                }

                // If the data does not exist, perform POST request to insert patient data
                String postMedicalRecordUrl = "http://localhost:8081/mhs/medicalRecord";
                ResponseEntity<String> postResponse = restTemplate.postForEntity(postMedicalRecordUrl, patientData, String.class);
                return postResponse.getStatusCode().is2xxSuccessful();
            } else {
                // Log the error if GET request is not successful
                System.err.println("GET request to " + getPatientDataUrl + " failed with status: " + getResponse.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new PatientNotFoundException("Patient with CF " + cf + " not found.");
            }
            // Log the detailed error message
            System.err.println("HTTP Client Error during GET request to " + getPatientDataUrl + ": " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            // Handle other exceptions (log the error, etc.)
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePatientData(PatientData patientData) throws PatientNotFoundException {
        String cf = patientData.getCf();
        String getPatientDataUrl = "http://localhost:8080/prs/patientData/" + cf;
        String putMedicalRecordUrl = "http://localhost:8081/mhs/medicalRecord/" + cf;

        try {
            // Check if patient exists in Patient Record Service
            ResponseEntity<String> getResponse = restTemplate.getForEntity(getPatientDataUrl, String.class);

            if (getResponse.getStatusCode().is2xxSuccessful()) {
                // Perform PUT request to update patient data
                HttpEntity<PatientData> requestEntity = new HttpEntity<>(patientData);
                ResponseEntity<String> putResponse = restTemplate.exchange(putMedicalRecordUrl, HttpMethod.PUT, requestEntity, String.class);
                return putResponse.getStatusCode().is2xxSuccessful();
            } else {
                // Log the error if GET request is not successful
                System.err.println("GET request to " + getPatientDataUrl + " failed with status: " + getResponse.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new PatientNotFoundException("Patient with CF " + cf + " not found.");
            }
            // Log the detailed error message
            System.err.println("HTTP Client Error during GET request to " + getPatientDataUrl + ": " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            // Handle other exceptions (log the error, etc.)
            e.printStackTrace();
        }
        return false;
    }
}
