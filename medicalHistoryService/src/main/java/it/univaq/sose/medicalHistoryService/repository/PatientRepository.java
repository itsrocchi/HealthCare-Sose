package it.univaq.sose.medicalHistoryService.repository;

import it.univaq.sose.medicalHistoryService.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
}