package it.univaq.sose.client.feign;

import it.univaq.sose.client.config.FeignConfig;
import it.univaq.sose.client.model.Doctor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "doctorRecordService", url="http://localhost:9000/drs", configuration = FeignConfig.class)
public interface DoctorServiceClient {

    @GetMapping("/doctors/{id}")
    Doctor getDoctorById(@PathVariable("id") String id);

    @GetMapping("/doctors")
    List<Doctor> getAllDoctors();

    @PostMapping("/doctors")
    Doctor createDoctor(@RequestBody Doctor doctor);

    @PutMapping("/doctors/{id}")
    Doctor updateDoctor(@PathVariable("id") String id, @RequestBody Doctor doctor);

    @DeleteMapping("/doctors/{id}")
    void deleteDoctor(@PathVariable("id") String id);
}

