package it.univaq.sose.medicalHistoryService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

//@Entity
public class MedicalRecord {

    //@Id
    private Long id;
    private Patient patient;
    private List<String> pastDiseases;
    private List<Allergies> allergies;
    private List<Vaccinations> vaccinations;
    private List<Medications> medications;
    private String notes;

    // create a static variable idcounter and set it to 0
    private static Long idCounter = 0L;


    //create getters and setters for this class
    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = idCounter;
        idCounter++;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<String> getPastDiseases() {
        return pastDiseases;
    }

    public void setPastDiseases(List<String> pastDiseases) {
        this.pastDiseases = pastDiseases;
    }

    public List<Allergies> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergies> allergies) {
        this.allergies = allergies;
    }

    public List<Vaccinations> getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(List<Vaccinations> vaccinations) {
        this.vaccinations = vaccinations;
    }

    public List<Medications> getMedications() {
        return medications;
    }

    public void setMedications(List<Medications> medications) {
        this.medications = medications;
    }



}

