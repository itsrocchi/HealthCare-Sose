package it.univaq.sose.medicalHistoryService.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @Column(name = "CF", nullable = false, unique = true)
    private String CF;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "address")
    private String address;

    @Column(name = "email", nullable = false)
    private String email;

    // Constructors, getters, setters, toString, equals, and hashCode methods

    public Patient() {}

    public Patient(String CF, String name, String surname, Gender gender, int age, String address, String email) {
        this.CF = CF;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.email = email;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "CF='" + CF + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        if (!CF.equals(patient.CF)) return false;
        if (!name.equals(patient.name)) return false;
        if (!surname.equals(patient.surname)) return false;
        if (gender != patient.gender) return false;
        if (age != patient.age) return false;
        if (address != null ? !address.equals(patient.address) : patient.address != null) return false;
        return email.equals(patient.email);
    }

    @Override
    public int hashCode() {
        int result = CF.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + gender.hashCode();
        result = address != null ? address.hashCode() : 0;
        result = 31 * result + email.hashCode();
        return result;
    }
}