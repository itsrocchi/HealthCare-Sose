
package it.univaq.sose.doctorRecordService.controller;

import it.univaq.sose.doctorRecordService.model.Doctor;
import it.univaq.sose.doctorRecordService.service.DrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drs")
public class DrsController {

    @Autowired
    private DrsService service;

    @GetMapping("/doctorData/{id}")
    public ResponseEntity<Doctor> getDoctorData(@PathVariable String id){
        Optional<Doctor> record = service.getDoctorByCf(id);
        return record.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/doctorData")
    public ResponseEntity<List<Doctor>> getAllDoctor(){
        List<Doctor> records = service.getAllDoctor();
        return ResponseEntity.ok((List<Doctor>) records);
    }

    @PostMapping("/doctorData")
    public ResponseEntity<Doctor> createDoctorData(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(service.saveDoctor(doctor));
    }

    @PutMapping("/doctorData/{id}")
    public ResponseEntity<Doctor> updateDoctorData(@PathVariable String id, @RequestBody Doctor doctorRecord) {
        Doctor updatedRecord = service.updateDoctor(id, doctorRecord);
        return updatedRecord != null ? ResponseEntity.ok(updatedRecord) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/doctorData/{id}")
    public ResponseEntity<Void> deleteDoctorData(@PathVariable String id) {
        service.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

}
