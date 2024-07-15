package it.univaq.sose.doctorRecordService.repository;

import it.univaq.sose.doctorRecordService.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
}