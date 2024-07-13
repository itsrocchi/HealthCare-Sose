package it.univaq.sose.appointmentSchedulingService;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "patient_cf", nullable = false)
    private String patientCF;
    
	@Column(name = "doctor_Id", nullable = false)
    private Long doctorId;
	
	@Column(name = "appointment_datetime", nullable = false)
    private String appointmentDateTime;
	
	@Column(name="info", nullable = false)
    private String info;
	
	@Column(name="status", nullable = false)
    private String status;

    
    public Appointment(Long id, String patientCF, Long doctorId, String appointmentDateTime, String info,
			String status) {
		super();
		this.id = id;
		this.patientCF = patientCF;
		this.doctorId = doctorId;
		this.appointmentDateTime = appointmentDateTime;
		this.info = info;
		this.status = status;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(String newAppointmentTime) {
        this.appointmentDateTime = newAppointmentTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getPatientCF() {
		return patientCF;
	}

	public void setPatientCF(String patientCF) {
		this.patientCF = patientCF;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
}
