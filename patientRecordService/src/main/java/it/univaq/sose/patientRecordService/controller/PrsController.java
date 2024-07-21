
package it.univaq.sose.patientRecordService.controller;

import it.univaq.sose.patientRecordService.model.Patient;
import it.univaq.sose.patientRecordService.service.PrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> deletePatientData(@PathVariable String CF) {
        if (!service.existsByCf(CF)) {
            return ResponseEntity.notFound().build();
        }
        service.deletePatient(CF);
        return ResponseEntity.ok().build();
    }
    

}
