package it.univaq.sose.doctorRecordService.service;

import it.univaq.sose.doctorRecordService.model.Doctor;
import it.univaq.sose.doctorRecordService.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrsService {

    @Autowired
    private DoctorRepository repository;

    public Optional<Doctor> getDoctorByCf(String id) {
        return repository.findById(id);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return repository.save(doctor);
    }

    public Doctor updateDoctor(String id, Doctor doctor) {
        if (repository.existsById(id) ) {
            doctor.setId(id);
            return repository.save(doctor);
        }
        return null;
    }

    public void deleteDoctor(String id) {
        repository.deleteById(id);
    }

    public List<Doctor> getAllDoctor() {
        return repository.findAll();
    }


}
