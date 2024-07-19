package it.univaq.sose.medicalHistoryService.service;

import it.univaq.sose.medicalHistoryService.MedicalHistoryServiceApplication;
import it.univaq.sose.medicalHistoryService.model.MedicalRecord;
import it.univaq.sose.medicalHistoryService.repository.MedicalRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class MhsService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    private static final Logger logger = LoggerFactory.getLogger(MedicalHistoryServiceApplication.class);

    public Optional<MedicalRecord> getMedicalRecord(String cf) {
        logger.info("Fetching medical record data for CF: " + cf);
        try {
            TimeUnit.SECONDS.sleep(5); // 5-second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Completed fetching medical record data for CF: " + cf);
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
