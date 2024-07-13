package it.univaq.sose.appointmentSchedulingService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface AppointmentService {

    @WebMethod
    boolean cancelAppointment(Long appointmentId);

    @WebMethod
    List<String> getAppointmentsByDoctor(Long doctorId);

    @WebMethod
    List<String> getAppointmentsByPatient(String patientCF);

    @WebMethod
    boolean updateAppointment(Long appointmentId, String newAppointmentTime);

    @WebMethod
	boolean scheduleAppointment(Long appointmentId, String patientCF, Long doctorId, String appointmentDateTime, String info,
			String status);
}