package it.univaq.sose.client.controller;

import it.univaq.sose.client.feign.DoctorServiceClient;
import it.univaq.sose.client.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private DoctorServiceClient doctorServiceClient;

    public DoctorController(DoctorServiceClient doctorServiceClient){
        this.doctorServiceClient = doctorServiceClient;
    }

    @GetMapping("/doctors/{id}")
    public String getDoctorById(@PathVariable("id") String id, Model model) {
        Doctor doctor = doctorServiceClient.getDoctorById(id);
        model.addAttribute("doctor", doctor);
        return "doctor";
    }

    @GetMapping("/doctors")
    public String getAllDoctors(Model model) {
        List<Doctor> doctors = doctorServiceClient.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctors";
    }

    @PostMapping("/doctors")
    public String createDoctor(@ModelAttribute Doctor doctor) {
        doctorServiceClient.createDoctor(doctor);
        return "redirect:/doctors";
    }

    @PutMapping("/doctors/{id}")
    public String updateDoctor(@PathVariable("id") String id, @ModelAttribute Doctor doctor) {
        doctorServiceClient.updateDoctor(id, doctor);
        return "redirect:/doctors/" + id;
    }

    @DeleteMapping("/doctors/{id}")
    public String deleteDoctor(@PathVariable("id") String id) {
        doctorServiceClient.deleteDoctor(id);
        return "redirect:/doctors";
    }
}
