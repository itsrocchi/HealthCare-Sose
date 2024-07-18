package it.univaq.sose.healthcareManagementService.controller;

import it.univaq.sose.healthcareManagementService.exception.PatientNotFoundException;
import it.univaq.sose.healthcareManagementService.service.InsertService;
import it.univaq.sose.healthcareManagementService.util.PatientData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hms/medicalRecord")
public class InsertController {

    @Autowired
    private InsertService insertService;

    @PostMapping
    public ResponseEntity<String> insertPatientData(@RequestBody PatientData patientData) {
        try {
            boolean success = insertService.processPatientData(patientData);
            if (success) {
                return new ResponseEntity<>("Data inserted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Data already exists", HttpStatus.CONFLICT);
            }
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{cf}")
    public ResponseEntity<String> updatePatientData(@PathVariable String cf, @RequestBody PatientData patientData) {
        try {
            // Ensure the CF in the path matches the CF in the request body
            if (!cf.equals(patientData.getCf())) {
                return new ResponseEntity<>("CF in path and body do not match", HttpStatus.BAD_REQUEST);
            }

            boolean success = insertService.updatePatientData(patientData);
            if (success) {
                return new ResponseEntity<>("Data updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update data", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
