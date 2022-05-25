package com.hospital;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.OneToMany;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.CreatedBy;

import com.hospital.entities.Medecin;
import com.hospital.entities.Patient;
import com.hospital.entities.RendezVous;
import com.hospital.entities.StatusRDV;
import com.hospital.repositories.MedecinRepository;
import com.hospital.repositories.PatientRepository;
import com.hospital.repositories.RendezVousRepository;

import antlr.CharQueue;
import lombok.val;


@SpringBootApplication
public class HospitalManagementSystemApplication{

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementSystemApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository) {
		
		return args -> {
			
			Stream.of("Tom", "Bob", "Mike", "Sara").forEach(name ->{
				Patient patient= new Patient();
				patient.setNom(name);
				patient.setDateNaissance(new Date());
				patient.setMalade(Math.random()<0.5 ?true : false);
				patientRepository.save(patient);
			});
			
			Stream.of("jack", "Michael", "Paul", "Katy").forEach(name ->{
				Medecin medecin= new Medecin();
				medecin.setNom(name);
				medecin.setEmail(name+"@gmail.com");
				medecin.setSpecialite(Math.random()<0.5 ?"Generaliste" : "Specialiste");
				medecinRepository.save(medecin);
			});
			
			//Ici, je vais créer un rdv pour chaque patient avec un medcin avec le meme id
			patientRepository.findAll().forEach(patient ->{
				RendezVous rendezVous= new RendezVous();
				rendezVous.setPatient(patient);
				rendezVous.setMedecin(medecinRepository.findById(patient.getId()).get());
				rendezVous.setDate(new Date());
				rendezVous.setStatus(StatusRDV.PENDING);
				rendezVousRepository.save(rendezVous);
			});
			
			
		};
	}

}
