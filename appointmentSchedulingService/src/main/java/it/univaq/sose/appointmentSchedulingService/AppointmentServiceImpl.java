package it.univaq.sose.appointmentSchedulingService;

import javax.annotation.PreDestroy;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "it.univaq.sose.appointmentSchedulingService.AppointmentService")
public class AppointmentServiceImpl implements AppointmentService {

    private RestClient restClient;
    private EntityManagerFactory emf;
    private EntityManager em;

    public AppointmentServiceImpl() {
        this.restClient = new RestClient();
        this.emf = Persistence.createEntityManagerFactory("appointmentPU");
        this.em = emf.createEntityManager();
    }

    @Override
    public boolean scheduleAppointment(Long id, String patientCF, Long doctorId, String appointmentDateTime, String info,
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
    public boolean cancelAppointment(Long appointmentId) {
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
    public List<String> getAppointmentsByDoctor(Long doctorId) {
        TypedQuery<Appointment> query = em.createQuery("SELECT a FROM Appointment a WHERE a.doctorId = :doctorId", Appointment.class);
        query.setParameter("doctorId", doctorId);
        List<Appointment> appointments = query.getResultList();
        return appointments.stream().map(Appointment::toString).collect(Collectors.toList());
    }

    @Override
    public List<String> getAppointmentsByPatient(String patientCF) {
        TypedQuery<Appointment> query = em.createQuery("SELECT a FROM Appointment a WHERE a.patientCF = :patientCF", Appointment.class);
        query.setParameter("patientCF", patientCF);
        List<Appointment> appointments = query.getResultList();
        return appointments.stream().map(Appointment::toString).collect(Collectors.toList());
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
