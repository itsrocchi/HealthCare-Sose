package it.univaq.sose.healthcareManagementService.util;

import java.util.List;

public class PatientData {

    private String cf;
    private List<String> pastDiseases;
    private List<String> allergies;
    private List<String> vaccinations;
    private List<String> medications;
    private String notes;

    // Getters and setters

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public List<String> getPastDiseases() {
        return pastDiseases;
    }

    public void setPastDiseases(List<String> pastDiseases) {
        this.pastDiseases = pastDiseases;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public List<String> getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(List<String> vaccinations) {
        this.vaccinations = vaccinations;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}