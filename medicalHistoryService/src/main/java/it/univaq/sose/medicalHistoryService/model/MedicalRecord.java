package it.univaq.sose.medicalHistoryService.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "medical_record")
public class MedicalRecord {

    @Id
    @Column(name = "CF", nullable = false)
    private String CF; // Changed to be the primary key

    @Column(name = "diseases")
    private List<String> pastDiseases;

    @Column(name = "allergies")
    private List<String> allergies;


    @Column(name = "vaccinations")
    private List<String> vaccinations;


    @Column(name = "medications")
    private List<String> medications;

    @Column(name = "notes")
    private String notes;

    // Constructors, getters, setters, toString, equals, and hashCode methods

    public MedicalRecord() {}

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

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "CF='" + CF + '\'' +
                ", pastDiseases=" + pastDiseases +
                ", allergies=" + allergies +
                ", vaccinations=" + vaccinations +
                ", medications=" + medications +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalRecord that = (MedicalRecord) o;
        return CF.equals(that.CF);
    }

    @Override
    public int hashCode() {
        return CF.hashCode();
    }
}
