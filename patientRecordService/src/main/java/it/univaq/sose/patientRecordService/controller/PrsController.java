
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
