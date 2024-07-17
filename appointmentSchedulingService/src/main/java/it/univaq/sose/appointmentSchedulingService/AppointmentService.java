package it.univaq.sose.appointmentSchedulingService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface AppointmentService {

    @WebMethod
    boolean cancelAppointment(String appointmentId);

    @WebMethod
    List<String> getAppointmentsByDoctor(String doctorId);

    @WebMethod
    List<String> getAppointmentsByPatient(String patientCF);

    @WebMethod
	boolean scheduleAppointment(String appointmentId, String patientCF, String doctorId, String appointmentDateTime, String info,
			String status);

	List<String> getAppointmentById(String appointmentId);

	@WebMethod
	boolean updateAppointment(String appointmentId, String newAppointmentTime);

	List<String> getAllAppointments();
}

