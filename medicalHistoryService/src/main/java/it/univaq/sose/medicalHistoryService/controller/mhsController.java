package it.univaq.sose.medicalHistoryService.controller;

import it.univaq.sose.medicalHistoryService.model.MedicalRecord;
import it.univaq.sose.medicalHistoryService.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mhs")
public class mhsController {
    private final String PRS_BASE_URL = "<http://localhost:8080/prs>";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @GetMapping("/medicalRecord/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecord(@PathVariable Long id) {
        Optional<MedicalRecord> record = medicalRecordRepository.findById(id);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/medicalRecord")
    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords() {
        List<MedicalRecord> records = medicalRecordRepository.findAll();
        return ResponseEntity.ok(records);
    }

    @PostMapping("/medicalRecord")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        // Check if patient exists
        ResponseEntity<Void> response = restTemplate.getForEntity(PRS_BASE_URL + "/patientData/{CF}", Void.class, medicalRecord.getCF());
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(medicalRecordRepository.save(medicalRecord));
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/medicalRecord/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable Long id, @RequestBody MedicalRecord medicalRecord) {
        // Check if medical record exists
        if (!medicalRecordRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Check if patient exists
        ResponseEntity<Void> response = restTemplate.getForEntity(PRS_BASE_URL + "/patientData/{CF}", Void.class, medicalRecord.getCF());
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(medicalRecordRepository.save(medicalRecord));
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/medicalRecord/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
        if (!medicalRecordRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        medicalRecordRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
