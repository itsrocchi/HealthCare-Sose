package it.univaq.sose.appointmentSchedulingService;

import javax.annotation.PreDestroy;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "it.univaq.sose.appointmentSchedulingService.AppointmentService")
public class AppointmentServiceImpl implements AppointmentService {

    private RestClient restClient;
    private EntityManagerFactory emf;
    @PersistenceContext(unitName = "appointmentPU")
    private EntityManager em;

    public AppointmentServiceImpl() {
        this.restClient = new RestClient();
        this.emf = Persistence.createEntityManagerFactory("appointmentPU");
        this.em = emf.createEntityManager();
    }

    @Override
    public boolean scheduleAppointment(String id, String patientCF, String doctorId, String appointmentDateTime, String info,
                                       String status) {
        if (!restClient.patientExists(patientCF) || !restClient.doctorExists(doctorId)) {
            return false;
        }

        Appointment appointment = new Appointment(id, patientCF, doctorId, appointmentDateTime, info, status);
        em.getTransaction().begin();
        em.persist(appointment);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean cancelAppointment(String appointmentId) {
        Appointment appointment = em.find(Appointment.class, appointmentId);
        if (appointment == null) {
            return false;
        }

        em.getTransaction().begin();
        em.remove(appointment);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public List<String> getAppointmentsByDoctor(String doctorId) {
        TypedQuery<Appointment> query = em.createQuery("SELECT a FROM "+Appointment.class.getName()+" a WHERE a.doctorId = :doctorId", Appointment.class);
        query.setParameter("doctorId", doctorId);
        List<Appointment> appointments = query.getResultList();
        return appointments.stream().map(Appointment::toString).collect(Collectors.toList());
    }

    @Override
    public List<String> getAppointmentsByPatient(String patientCF) {
        System.out.println("getAppointmentsByPatient called with patientCF: " + patientCF);
        TypedQuery<Appointment> query = em.createQuery("SELECT a FROM it.univaq.sose.appointmentSchedulingService.Appointment a WHERE a.patientCF = :patientCF", Appointment.class);
        query.setParameter("patientCF", patientCF);
        List<Appointment> appointments = query.getResultList();
        System.out.println("Appointments retrieved: " + appointments.size());

        if (appointments.isEmpty()) {
            System.out.println("No appointments found for patientCF: " + patientCF);
            return new ArrayList<>(); // Return an empty list if no appointments are found
        }

        List<String> appointmentStrings = appointments.stream().map(Appointment::toString).collect(Collectors.toList());
        System.out.println("Returning appointments: " + appointmentStrings);
        return appointmentStrings;
    }
    
    @Override
    public boolean updateAppointment(Long appointmentId, String newAppointmentTime) {
        Appointment appointment = em.find(Appointment.class, appointmentId);
        if (appointment == null) {
            return false;
        }

        em.getTransaction().begin();
        appointment.setAppointmentDateTime(newAppointmentTime);
        em.getTransaction().commit();
        return true;
    }

    @PreDestroy
    public void close() {
        em.close();
        emf.close();
    }
}
