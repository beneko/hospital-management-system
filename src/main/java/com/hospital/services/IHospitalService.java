package com.hospital.services;

import com.hospital.entities.Consultation;
import com.hospital.entities.Medecin;
import com.hospital.entities.Patient;
import com.hospital.entities.RendezVous;

public interface IHospitalService {
	
	Patient savePatient(Patient patient);
	Medecin saveMedecin(Medecin medecin);
	RendezVous saveRendezVous(RendezVous rendezVous);
	Consultation saveConsultation(Consultation consultation);
}
