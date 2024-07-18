package it.univaq.sose.healthcareManagementService.controller;

import it.univaq.sose.healthcareManagementService.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/hms/statistical")
public class StatisticalController {

    @Autowired
    private StatisticalService service;

    @GetMapping("/average-age")
    public ResponseEntity<Double> getAverageAge() {
        double averageAge = service.calculateAverageAge();
        return ResponseEntity.ok(averageAge);
    }

    @GetMapping("/total-patients")
    public ResponseEntity<Long> getTotalPatients() {
        long totalPatients = service.countTotalPatients();
        return ResponseEntity.ok(totalPatients);
    }

    @GetMapping("/gender-percentage")
    public ResponseEntity<Map<String, Double>> getGenderPercentage() {
        Map<String, Double> genderPercentage = service.calculateGenderPercentage();
        return ResponseEntity.ok(genderPercentage);
    }

    @GetMapping("/nodisease-percentage")
    public ResponseEntity<Double> getDiseaseFreePercentage() {
        double diseaseFreePercentage = service.calculateDiseaseFreePercentage();
        return ResponseEntity.ok(diseaseFreePercentage);
    }

    /*@GetMapping("/{doctorCF}")
    public ResponseEntity<String> getAppointmentsByDoctor(@PathVariable String doctorCF) {
        String response = service.getAppointmentsByDoctor(doctorCF);
        return ResponseEntity.ok(response);
    }*/

}
