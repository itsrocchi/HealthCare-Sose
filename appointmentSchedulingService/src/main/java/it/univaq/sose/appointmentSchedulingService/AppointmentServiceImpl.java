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
import javax.persistence.metamodel.EntityType;
import java.util.Set;

@WebService(endpointInterface = "it.univaq.sose.appointmentSchedulingService.AppointmentService")
public class AppointmentServiceImpl implements AppointmentService {

    private RestClient restClient;
    private EntityManagerFactory emf;
    @PersistenceContext(unitName = "appointmentPU")
    private EntityManager em;
    

    public void checkEntityMappings(EntityManager em) {
        Set<EntityType<?>> entities = em.getMetamodel().getEntities();
        for (EntityType<?> entity : entities) {
            System.out.println("Entity: " + entity.getName());
        }
    }


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
        
        checkEntityMappings(em);

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
        
        if (appointments.isEmpty()) {
            System.out.println("No appointments found for doctor: " + doctorId);
            return new ArrayList<>(); // Return an empty list if no appointments are found
        }
        
        return appointments.stream().map(Appointment::toString).collect(Collectors.toList());
    }
    
    @Override
    public List<String> getAppointmentById(String appointmentId){
    	TypedQuery<Appointment> query = em.createQuery("SELECT a FROM "+Appointment.class.getName()+" a WHERE a.id = :appointmentId", Appointment.class);
        query.setParameter("appointmentId", appointmentId);
        List<Appointment> appointments = query.getResultList();
        
        if (appointments.isEmpty()) {
            System.out.println("No appointments found for id: " + appointmentId);
            return new ArrayList<>(); // Return an empty list if no appointments are found
        }
        
        return appointments.stream().map(Appointment::toString).collect(Collectors.toList());
    }

    @Override
    public List<String> getAppointmentsByPatient(String patientCF) {
        TypedQuery<Appointment> query = em.createQuery("SELECT a FROM it.univaq.sose.appointmentSchedulingService.Appointment a WHERE a.patientCF = :patientCF", Appointment.class);
        query.setParameter("patientCF", patientCF);
        List<Appointment> appointments = query.getResultList();

        if (appointments.isEmpty()) {
            System.out.println("No appointments found for patientCF: " + patientCF);
            return new ArrayList<>(); // Return an empty list if no appointments are found
        }

        List<String> appointmentStrings = appointments.stream().map(Appointment::toString).collect(Collectors.toList());
        return appointmentStrings;
        
    }
    
    @Override
    public List<String> getAllAppointments(){
    	TypedQuery<Appointment> query = em.createQuery("SELECT a FROM Appointment a", Appointment.class);
        List<Appointment> appointments = query.getResultList();
        
        if (appointments.isEmpty()) {
            System.out.println("No appointments found");
            return new ArrayList<>(); // Return an empty list if no appointments are found
        }
        
        return appointments.stream().map(Appointment::toString).collect(Collectors.toList());
    }
    
    @Override
    public boolean updateAppointment(String appointmentId, String newAppointmentTime) {
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

/**/
