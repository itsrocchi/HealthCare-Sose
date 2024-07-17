package it.univaq.sose.doctorRecordService;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Response;


import it.univaq.sose.doctorRecordService.model.Doctor;

public class DoctorRecordImpl implements DoctorRecord {
	
	private EntityManagerFactory emf;
    private EntityManager em;

    @PostConstruct
    public void init() {
        emf = Persistence.createEntityManagerFactory("doctorRecordPU");
        em = emf.createEntityManager();
    }

    @Override
    public Response getDoctorById(String id) {
        Doctor doctor = em.find(Doctor.class, id);
        if (doctor != null) {
            return Response.ok(doctor).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response getAllDoctors() {
        List<Doctor> doctors = em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();
        return Response.ok(doctors).build();
    }

    @Override
    public Response updateDoctor(String id, Doctor doctor) {
        Doctor existingDoctor = em.find(Doctor.class, id);
        if (existingDoctor != null) {
            em.getTransaction().begin();
            existingDoctor.setName(doctor.getName());
            existingDoctor.setSurname(doctor.getSurname());
            existingDoctor.setSpecialization(doctor.getSpecialization());
            em.getTransaction().commit();
            return Response.ok(existingDoctor).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response deleteDoctor(String id) {
        Doctor doctor = em.find(Doctor.class, id);
        if (doctor != null) {
            em.getTransaction().begin();
            em.remove(doctor);
            em.getTransaction().commit();
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response createDoctor(Doctor doctor) {
        em.getTransaction().begin();
        em.persist(doctor);
        em.getTransaction().commit();
        return Response.ok(doctor).build();
    }

    @PreDestroy
    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
