package it.univaq.sose.appointmentSchedulingService;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface AppointmentService {

	@WebMethod
	public int sum(int a, int b);
	
}
