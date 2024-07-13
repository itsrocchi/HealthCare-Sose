package it.univaq.sose.appointmentSchedulingService;

import javax.jws.WebService;


@WebService(endpointInterface = "it.univaq.sose.appointmentSchedulingService.AppointmentService")
public class AppointmentServiceImpl implements AppointmentService{

	public int sum(int a, int b) {
		return a+b;
	}
	
}
