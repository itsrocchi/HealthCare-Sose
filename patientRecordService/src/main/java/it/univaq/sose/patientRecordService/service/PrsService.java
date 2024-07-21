package it.univaq.sose.patientRecordService.service;

import it.univaq.sose.patientRecordService.PatientRecordServiceApplication;
import it.univaq.sose.patientRecordService.model.Patient;
import it.univaq.sose.patientRecordService.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class PrsService {

    @Autowired
    private PatientRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(PatientRecordServiceApplication.class);

    public Optional<Patient> getPatientByCf(String cf) {
        logger.info("Fetching patient data for CF: " + cf);
        try {
            TimeUnit.SECONDS.sleep(8); // 5-second delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Completed fetching patient data for CF: " + cf);
        return repository.findById(cf);
    }

    public Patient savePatient(Patient patient) {
        return repository.save(patient);
    }

    public Patient updatePatient(String cf, Patient patient) {
        if (repository.existsById(cf)) {
            patient.setCF(cf);
            return repository.save(patient);
        }
        return null;
    }

    public void deletePatient(String cf) {
        repository.deleteById(cf);
    }

    public boolean existsByCf(String cf) {
        return repository.existsById(cf);
    }

    public List<Patient> getAllPatient() {
        return repository.findAll();
    }


}
