package it.univaq.sose.doctorRecordService.model;

import javax.xml.bind.annotation.XmlRootElement;

import jakarta.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table(name = "doctors")
@XmlRootElement(name = "Doctor")
public class Doctor {

	@Id
	@Column(name = "id", unique = true)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
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