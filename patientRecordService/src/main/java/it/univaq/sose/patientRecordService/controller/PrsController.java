
package it.univaq.sose.patientRecordService.controller;

import it.univaq.sose.patientRecordService.model.Patient;
import it.univaq.sose.patientRecordService.service.PrsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/prs")
public class PrsController {

    @Autowired
    private PrsService service;

    @GetMapping("/patientData/{CF}")
    public ResponseEntity<Patient> getPatientData(@PathVariable String CF){
        Optional<Patient> record = service.getPatientByCf(CF);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/patientData")
    public ResponseEntity<List<Patient>> getAllPatient(){
        List<Patient> records = service.getAllPatient();
        return ResponseEntity.ok((List<Patient>) records);
    }

    @PostMapping("/patientData")
    public ResponseEntity<Patient> createPatientData(@RequestBody Patient patient) {
        return ResponseEntity.ok(service.savePatient(patient));
    }

    @PutMapping("/patientData/{CF}")
    public ResponseEntity<Patient> updatePatientData(@PathVariable String CF, @RequestBody Patient patientRecord) {
        Patient updatedRecord = service.updatePatient(CF, patientRecord);
        return updatedRecord != null ? ResponseEntity.ok(updatedRecord) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/patientData/{CF}")
    public ResponseEntity<Void> deletePatientData(@PathVariable String CF) {
        service.deletePatient(CF);
        return ResponseEntity.noContent().build();
    }



}

/*package it.univaq.sose.patientRecordService.controller;

import it.univaq.sose.patientRecordService.model.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/prs")
public class PrsController {

    // Simulazione di un repository in memoria per i dati dei pazienti
    private Map<String, Patient> patientRecords = new HashMap<>();

    @GetMapping("/patientData/{CF}")
    public ResponseEntity<Patient> getPatientData(@PathVariable String CF) {
        Optional<Patient> record = getPatientByCf(CF);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/patientData")
    public ResponseEntity<List<Patient>> getAllPatientData() {
        List<Patient> records = getAllPatientRecords();
        return ResponseEntity.ok(records);
    }

    @PostMapping("/patientData")
    public ResponseEntity<Patient> createPatientData(@RequestBody Patient patient) {
        patientRecords.put(patient.getCF(), patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(patient);
    }

    @PutMapping("/patientData/{CF}")
    public ResponseEntity<Patient> updatePatientData(@PathVariable String CF, @RequestBody Patient patient) {
        if (patientRecords.containsKey(CF)) {
            patient.setCF(CF);
            patientRecords.put(CF, patient);
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/patientData/{CF}")
    public ResponseEntity<Void> deletePatientData(@PathVariable String CF) {
        if (patientRecords.containsKey(CF)) {
            patientRecords.remove(CF);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodi privati per la gestione dei dati
    private Optional<Patient> getPatientByCf(String CF) {
        return Optional.ofNullable(patientRecords.get(CF));
    }

    private List<Patient> getAllPatientRecords() {
        return List.copyOf(patientRecords.values());
    }
} */