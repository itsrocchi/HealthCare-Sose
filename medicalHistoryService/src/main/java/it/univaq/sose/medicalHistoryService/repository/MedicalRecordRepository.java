package it.univaq.sose.medicalHistoryService.repository;

import it.univaq.sose.medicalHistoryService.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}