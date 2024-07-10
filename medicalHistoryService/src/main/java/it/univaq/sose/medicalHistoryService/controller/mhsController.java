package it.univaq.sose.medicalHistoryService.controller;

import it.univaq.sose.medicalHistoryService.model.MedicalRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/*todo fix the id stuff*/

@RestController
@RequestMapping("/mhs")
public class mhsController {

    // Simulazione di un repository in memoria per i record medici
    private Map<Long, MedicalRecord> medicalRecords = new HashMap<>();

    @GetMapping("/medicalRecord/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecord(@PathVariable long id) {
        if (medicalRecords.containsKey(id)) {
            return ResponseEntity.ok(medicalRecords.get(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/medicalRecord")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecords.put(medicalRecord.getId(), medicalRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecord);
    }

    @PutMapping("/medicalRecord/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable long id, @RequestBody MedicalRecord medicalRecord) {
        if (medicalRecords.containsKey(id)) {
            medicalRecord.setId(id);
            medicalRecords.put(id, medicalRecord);
            return ResponseEntity.ok(medicalRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/medicalRecord/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable String id) {
        if (medicalRecords.containsKey(id)) {
            medicalRecords.remove(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


/*package it.univaq.sose.medicalHistoryService.controller;

import it.univaq.sose.medicalHistoryService.model.MedicalRecord;
import it.univaq.sose.medicalHistoryService.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/mhs")
public class mhsController {

    @Autowired
    private MedicalRecordRepository repository;

    @GetMapping("/medicalRecord/{id}")
    public ResponseEntity<MedicalRecord> getMedicalHistory(@PathVariable String id) {
        Optional<MedicalRecord> history = getMedicalRecordById(id);
        return history.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/medicalHistory")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return ResponseEntity.ok(saveMedicalRecord(medicalRecord));
    }

    @PutMapping("/medicalHistory/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable String id, @RequestBody MedicalRecord medicalRecord) {
        MedicalRecord updatedHistory = updateMr(id, medicalRecord);
        return updatedHistory != null ? ResponseEntity.ok(updatedHistory) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/medicalHistory/{id}")
    public ResponseEntity<Void> deleteMedicalHistory(@PathVariable String id) {
        deleteMr(id);
        return ResponseEntity.noContent().build();
    }


    //metodi privati per il recupero, salvataggio, aggiornamento e cancellazione di un record medico
    private Optional<MedicalRecord> getMedicalRecordById(String id) {
        return repository.findById(id);
    }

    private MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        return repository.save(medicalRecord);
    }

    private MedicalRecord updateMr(String id, MedicalRecord medicalRecord){
        if (repository.existsById(id)) {
            medicalRecord.setId(Long.valueOf(id));
            return repository.save(medicalRecord);
        }
        return null;
    }

    private void deleteMr(String id) {
        repository.deleteById(id);
    }

}*/