package it.univaq.sose.medicalHistoryService.service;

import it.univaq.sose.medicalHistoryService.model.MedicalRecord;
import it.univaq.sose.medicalHistoryService.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class MhsService {

    private final String PRS_BASE_URL = "http://localhost:8080/prs";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public Optional<MedicalRecord> getMedicalRecord(Long id) {
        return medicalRecordRepository.findById(id);
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    public ResponseEntity<MedicalRecord> createMedicalRecord(MedicalRecord medicalRecord) {
        ResponseEntity<Void> response = restTemplate.getForEntity(PRS_BASE_URL + "/patientData/{CF}", Void.class, medicalRecord.getCF());
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(medicalRecordRepository.save(medicalRecord));
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    public ResponseEntity<MedicalRecord> updateMedicalRecord(Long id, MedicalRecord medicalRecord) {
        if (!medicalRecordRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        ResponseEntity<Void> response = restTemplate.getForEntity(PRS_BASE_URL + "/patientData/{CF}", Void.class, medicalRecord.getCF());
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(medicalRecordRepository.save(medicalRecord));
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    public ResponseEntity<Void> deleteMedicalRecord(Long id) {
        if (!medicalRecordRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        medicalRecordRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}