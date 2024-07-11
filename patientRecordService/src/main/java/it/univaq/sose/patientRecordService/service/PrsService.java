package it.univaq.sose.patientRecordService.service;

import it.univaq.sose.patientRecordService.model.Patient;
import it.univaq.sose.patientRecordService.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class PrsService {

    @Autowired
    private PatientRepository repository;

    public Optional<Patient> getPatientByCf(String cf) {
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

    public List<Patient> getAllPatient() {
        return repository.findAll();
    }


}
