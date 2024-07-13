package it.univaq.sose.appointmentSchedulingService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class RestClient {

	//modificare questi url
    private static final String PATIENT_RECORD_SERVICE_URL = "http://localhost:8080/prs/patientData/";
    private static final String DOCTOR_RECORD_SERVICE_URL = "http://localhost:8083/drs/doctorData/"; //service still to be created

    private Client client;

    public RestClient() {
        client = ClientBuilder.newClient();
    }

    public boolean patientExists(String patientCF) {
        Response response = client.target(PATIENT_RECORD_SERVICE_URL + patientCF).request().get();
        return response.getStatus() == 200;
    }

    public boolean doctorExists(Long doctorId) {
        Response response = client.target(DOCTOR_RECORD_SERVICE_URL + doctorId).request().get();
        return response.getStatus() == 200;
    }
}
