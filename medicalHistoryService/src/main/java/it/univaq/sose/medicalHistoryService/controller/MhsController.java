package it.univaq.sose.medicalHistoryService.controller;

import it.univaq.sose.medicalHistoryService.model.MedicalRecord;
import it.univaq.sose.medicalHistoryService.service.MhsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mhs")
public class MhsController {

    @Autowired
    private MhsService service;

    @GetMapping("/medicalRecord/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecord(@PathVariable Long id) {
        Optional<MedicalRecord> record = service.getMedicalRecord(id);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/medicalRecord")
    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords() {
        List<MedicalRecord> records = service.getAllMedicalRecords();
        return ResponseEntity.ok(records);
    }

    @PostMapping("/medicalRecord")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return service.createMedicalRecord(medicalRecord);
    }

    @PutMapping("/medicalRecord/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable Long id, @RequestBody MedicalRecord medicalRecord) {
        return service.updateMedicalRecord(id, medicalRecord);
    }

    @DeleteMapping("/medicalRecord/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
        return service.deleteMedicalRecord(id);
    }
}
