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

    @GetMapping("/medicalRecord/{cf}")
    public ResponseEntity<MedicalRecord> getMedicalRecord(@PathVariable String cf) {
        Optional<MedicalRecord> record = service.getMedicalRecord(cf);
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

    @PutMapping("/medicalRecord/{cf}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable String cf, @RequestBody MedicalRecord medicalRecord) {
        return service.updateMedicalRecord(cf, medicalRecord);
    }

    @DeleteMapping("/medicalRecord/{cf}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable String cf) {
        return service.deleteMedicalRecord(cf);
    }
}
