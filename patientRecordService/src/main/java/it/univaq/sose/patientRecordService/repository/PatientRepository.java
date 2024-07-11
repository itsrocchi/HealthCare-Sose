package it.univaq.sose.patientRecordService.repository;

import it.univaq.sose.patientRecordService.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
}