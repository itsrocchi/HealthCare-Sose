package it.univaq.sose.doctorRecordService.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @Column(name = "id", unique = true)
    private String id; //not a number but a string (like cf)

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "specialization", nullable = false)
    private String specialization;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }

    public Doctor(String id, String name, String surname, String specialization) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.specialization = specialization;
    }

    public Doctor() {
    }



}