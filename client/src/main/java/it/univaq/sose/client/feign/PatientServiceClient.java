package it.univaq.sose.client.feign;


import it.univaq.sose.client.config.FeignConfig;
import it.univaq.sose.client.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name= "patientRecordService", url="http://localhost:9000/prs", configuration = FeignConfig.class)
public interface PatientServiceClient {

    @GetMapping("/patients/{CF}")
    Patient getPatientData(@PathVariable String CF);

    @GetMapping("/patients")
    List<Patient> getAllPatients();

    @PostMapping("/patients")
    Patient createPatient(@RequestBody Patient patient);

    @PutMapping("/patients/{CF}")
    Patient updatePatient(@PathVariable String CF, @RequestBody Patient patient);

    @DeleteMapping("/patients/{CF}")
    void deletePatient(@PathVariable String CF);

}
