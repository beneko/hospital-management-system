package com.hospital;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hospital.entities.Consultation;
import com.hospital.entities.Medecin;
import com.hospital.entities.Patient;
import com.hospital.entities.RendezVous;
import com.hospital.entities.StatusRDV;
import com.hospital.repositories.ConsultationRepository;
import com.hospital.repositories.MedecinRepository;
import com.hospital.repositories.PatientRepository;
import com.hospital.repositories.RendezVousRepository;
import com.hospital.services.IHospitalService;



@SpringBootApplication
public class HospitalManagementSystemApplication{

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementSystemApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(
			IHospitalService hospitalService,
			PatientRepository patientRepository,
			MedecinRepository medecinRepository,
			RendezVousRepository rendezVousRepository
			) {
		
		return args -> {
			
			Stream.of("Tom", "Bob", "Mike", "Sara").forEach(name ->{
				Patient patient= new Patient();
				patient.setNom(name);
				patient.setDateNaissance(new Date());
				patient.setMalade(Math.random()<0.5 ?true : false);
				hospitalService.savePatient(patient);
			});
			
			Stream.of("jack", "Michael", "Paul", "Katy").forEach(name ->{
				Medecin medecin= new Medecin();
				medecin.setNom(name);
				medecin.setEmail(name+"@gmail.com");
				medecin.setSpecialite(Math.random()<0.5 ?"Generaliste" : "Specialiste");
				hospitalService.saveMedecin(medecin);
			});
			
			//Ici, je vais créer un rdv pour chaque patient avec un medcin avec le meme id
			patientRepository.findAll().forEach(patient ->{
				RendezVous rendezVous= new RendezVous();
				rendezVous.setPatient(patient);
				rendezVous.setMedecin(medecinRepository.findById(patient.getId()).get());
				rendezVous.setDate(new Date());
				rendezVous.setStatus(StatusRDV.PENDING);
				hospitalService.saveRendezVous(rendezVous);
			});
			
			//Ici, je vais créer une Consultation pour chaque Rdv
			rendezVousRepository.findAll().forEach(rdv ->{
				Consultation consultation= new Consultation();
				consultation.setDateConsultation(new Date());
				consultation.setRapport("rapport de la consultaion ...");
				consultation.setRendezVous(rdv);
				hospitalService.saveConsultation(consultation);
			});
			
			
		};
	}

}
