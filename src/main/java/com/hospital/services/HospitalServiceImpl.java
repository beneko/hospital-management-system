package com.hospital.services;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hospital.entities.Consultation;
import com.hospital.entities.Medecin;
import com.hospital.entities.Patient;
import com.hospital.entities.RendezVous;
import com.hospital.repositories.ConsultationRepository;
import com.hospital.repositories.MedecinRepository;
import com.hospital.repositories.PatientRepository;
import com.hospital.repositories.RendezVousRepository;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService{
	
	private PatientRepository patientRepository;
	private MedecinRepository medecinRepository;
	private RendezVousRepository rendezVousRepository;
	private ConsultationRepository consultationRepository;

	public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository,
			RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
		super();
		this.patientRepository = patientRepository;
		this.medecinRepository = medecinRepository;
		this.rendezVousRepository = rendezVousRepository;
		this.consultationRepository = consultationRepository;
	}

	@Override
	public Patient savePatient(Patient patient) {
		
		return patientRepository.save(patient);
	}

	@Override
	public Medecin saveMedecin(Medecin medecin) {
		
		return medecinRepository.save(medecin);
	}

	@Override
	public RendezVous saveRendezVous(RendezVous rendezVous) {
		rendezVous.setId(UUID.randomUUID().toString());
		return rendezVousRepository.save(rendezVous);
	}

	@Override
	public Consultation saveConsultation(Consultation consultation) {
		
		return consultationRepository.save(consultation);
	}
	
}
