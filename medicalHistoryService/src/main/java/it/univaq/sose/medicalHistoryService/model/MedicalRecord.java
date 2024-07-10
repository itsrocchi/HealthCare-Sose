package it.univaq.sose.medicalHistoryService.model;

import java.util.List;

public class MedicalRecord {

    private Long id;
    private String CF; // Changed from Patient to String for CF
    private List<String> pastDiseases;
    private List<Allergies> allergies;
    private List<Vaccinations> vaccinations;
    private List<Medications> medications;
    private String notes;

    private static Long idCounter = 0L;

    // Constructor to automatically set the ID
    public MedicalRecord() {
        this.id = idCounter++;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
