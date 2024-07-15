package it.univaq.sose.appointmentSchedulingService;

import javax.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

	@Id
    @Column(name = "app_id", nullable = false)
    private String id;
	
	@Column(name = "patient_cf", nullable = false)
    private String patientCF;
    
	@Column(name = "doctor_Id", nullable = false)
    private String doctorId;
	
	@Column(name = "appointment_datetime", nullable = false)
    private String appointmentDateTime;
	
	@Column(name="info", nullable = false)
    private String info;
	
	@Column(name="status", nullable = false)
    private String status;
	
	public Appointment() {
		
	}
	
	
	

    
    public Appointment(String id, String patientCF, String doctorId, String appointmentDateTime, String info,
			String status) {
		super();
		this.id = id;
		this.patientCF = patientCF;
		this.doctorId = doctorId;
		this.appointmentDateTime = appointmentDateTime;
		this.info = info;
		this.status = status;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
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

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
	@Override
	public String toString() {
	    return "Appointment{" +
	            "id='" + id + '\'' +
	            ", patientCF='" + patientCF + '\'' +
	            ", doctorId='" + doctorId + '\'' +
	            ", appointmentDateTime='" + appointmentDateTime + '\'' +
	            ", info='" + info + '\'' +
	            ", status='" + status + '\'' +
	            '}';
	}
}
