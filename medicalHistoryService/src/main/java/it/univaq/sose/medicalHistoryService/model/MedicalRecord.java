package it.univaq.sose.medicalHistoryService.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "medical_record")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CF", nullable = false)
    private String CF; // Changed from Patient to String for CF

    @Column(name = "diseases")
    private List<String> pastDiseases;

    @Column(name = "allergies")
    private List<Allergies> allergies;

    @Column(name = "vaccinations")
    private List<Vaccinations> vaccinations;

    @Column(name = "medications")
    private List<Medications> medications;

    @Column(name = "notes")
    private String notes;

    private static Long idCounter = 0L;

// Constructors, getters, setters, toString, equals, and hashCode methods

    public MedicalRecord() {
        this.id = ++idCounter;
    }

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

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", CF='" + CF +
                ", pastDiseases=" + pastDiseases +
                ", allergies=" + allergies +
                ", vaccinations=" + vaccinations +
                ", medications=" + medications +
                ", notes='" + notes +
        '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicalRecord that = (MedicalRecord) o;

        if (!id.equals(that.id)) return false;
        if (!CF.equals(that.CF)) return false;
        if (!pastDiseases.equals(that.pastDiseases)) return false;
        if (!allergies.equals(that.allergies)) return false;
        if (!vaccinations.equals(that.vaccinations)) return false;
        if (!medications.equals(that.medications)) return false;
        return notes != null ? notes.equals(that.notes) : that.notes == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + CF.hashCode();
        result = 31 * result + pastDiseases.hashCode();
        result = 31 * result + allergies.hashCode();
        result = 31 * result + vaccinations.hashCode();
        result = 31 * result + medications.hashCode();
        result = notes != null ? notes.hashCode() : 0;
        return result;
    }

}
