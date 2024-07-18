package it.univaq.sose.healthcareManagementService.service;

import it.univaq.sose.patientRecordService.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class StatisticalService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebServiceTemplate soapTemplate;

    public List<Patient> getAllPatients() {
        String url = "http://localhost:8080/prs/patientData";
        Patient[] patients = restTemplate.getForObject(url, Patient[].class);
        return Arrays.asList(patients);
    }

    public double calculateAverageAge() {
        List<Patient> patients = getAllPatients();
        return patients.stream()
                .mapToInt(Patient::getAge)
                .average()
                .orElse(0.0);
    }

    public long countTotalPatients() {
        List<Patient> patients = getAllPatients();
        return patients.size();
    }

    /*@Autowired
    private WebServiceTemplate webServiceTemplate;

    public String getAppointmentsByDoctor(String doctorCF) {
        // Construct the XML request
        String requestPayload =
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tns=\"http://appointmentSchedulingService.sose.univaq.it/\">" +
                        "   <soapenv:Header/>" +
                        "   <soapenv:Body>" +
                        "      <tns:getAppointmentsByDoctor>" +
                        "         <tns:arg0>" + doctorCF + "</tns:arg0>" +
                        "      </tns:getAppointmentsByDoctor>" +
                        "   </soapenv:Body>" +
                        "</soapenv:Envelope>";

        // Send the request
        StringSource source = new StringSource(requestPayload);
        StringSource response = (StringSource) webServiceTemplate.marshalSendAndReceive(source);

        // Process the response
        return parseResponse(response);
    }

    private String parseResponse(StringSource response) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(response.getInputStream());

            // Extract the desired values from the XML response
            NodeList nodes = document.getElementsByTagName("return");
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < nodes.getLength(); i++) {
                result.append(nodes.item(i).getTextContent()).append("\n");
            }

            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse SOAP response", e);
        }
    }*/
}


