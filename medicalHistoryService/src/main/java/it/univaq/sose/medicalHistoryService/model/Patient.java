package it.univaq.sose.medicalHistoryService.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

//@Entity
public class Patient {
  //  @Id
    private String CF;

    private String name;
    private String surname;
    private Date birthDate;
    private String address;
    private String email;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //create constructor for this class
    public Patient(String name, String surname, String CF, Date birthDate, String address, String email) {
        this.name = name;
        this.surname = surname;
        this.CF = CF;
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
    }

    public Patient() {
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", CF='" + CF + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return getName().equals(patient.getName()) &&
                getSurname().equals(patient.getSurname()) &&
                getCF().equals(patient.getCF()) &&
                getBirthDate().equals(patient.getBirthDate()) &&
                getAddress().equals(patient.getAddress()) &&
                getEmail().equals(patient.getEmail());
    }

    // method to create json object for this class
    public String toJson() {
        return "{" +
                "\"name\":\"" + name + '\"' +
                ", \"surname\":\"" + surname + '\"' +
                ", \"CF\":\"" + CF + '\"' +
                ", \"birthDate\":\"" + birthDate + '\"' +
                ", \"address\":\"" + address + '\"' +
                ", \"email\":\"" + email + '\"' +
                '}';
    }
}
