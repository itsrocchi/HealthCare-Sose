package it.univaq.sose.medicalHistoryService.repository;

import it.univaq.sose.medicalHistoryService.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, String> {
    Optional<MedicalRecord> findByCF(String CF);
    boolean existsByCF(String CF);
    void deleteByCF(String CF);
}
