package it.univaq.sose.medicalHistoryService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medical_record")
public class MedicalRecord {

    @Id
    @Column(name = "CF", nullable = false)
    private String CF; // Changed to be the primary key

    @Column(name = "diseases")
    private String pastDiseases;

    @Column(name = "allergies")
    private String allergies;


    @Column(name = "vaccinations")
    private String vaccinations;


    @Column(name = "medications")
    private String medications;

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

    public String getPastDiseases() {
        return pastDiseases;
    }

    public void setPastDiseases(String pastDiseases) {
        this.pastDiseases = pastDiseases;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(String vaccinations) {
        this.vaccinations = vaccinations;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
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
