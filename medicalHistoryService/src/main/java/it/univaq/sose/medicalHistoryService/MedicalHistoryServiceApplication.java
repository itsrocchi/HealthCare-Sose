package it.univaq.sose.medicalHistoryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicalHistoryServiceApplication {
	 // nb se deve funzionare con il database devi creare la classe interfaccia repository
	public static void main(String[] args) {
		SpringApplication.run(MedicalHistoryServiceApplication.class, args);
	}

}
