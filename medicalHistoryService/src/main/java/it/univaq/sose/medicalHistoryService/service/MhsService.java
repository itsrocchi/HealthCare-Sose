package it.univaq.sose.medicalHistoryService.service;

import it.univaq.sose.medicalHistoryService.model.MedicalRecord;
import it.univaq.sose.medicalHistoryService.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MhsService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public Optional<MedicalRecord> getMedicalRecord(String cf) {
        return medicalRecordRepository.findByCF(cf);
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    public ResponseEntity<MedicalRecord> createMedicalRecord(MedicalRecord medicalRecord) {
        return ResponseEntity.ok(medicalRecordRepository.save(medicalRecord));
    }

    public ResponseEntity<MedicalRecord> updateMedicalRecord(String cf, MedicalRecord medicalRecord) {
        if (!medicalRecordRepository.existsByCF(cf)) {
            return ResponseEntity.notFound().build();
        }
        medicalRecord.setCF(cf);
        return ResponseEntity.ok(medicalRecordRepository.save(medicalRecord));
    }

    public ResponseEntity<Void> deleteMedicalRecord(String cf) {
        if (!medicalRecordRepository.existsByCF(cf)) {
            return ResponseEntity.notFound().build();
        }
        medicalRecordRepository.deleteByCF(cf);
        return ResponseEntity.noContent().build();
    }
}
