package com.hospital.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class RendezVous {
	@Id
	private String id;
	private Date date;
	@Enumerated(EnumType.STRING)
	private StatusRDV Status;
	@ManyToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Patient patient;
	@ManyToOne
	private Medecin medecin;
	@OneToOne(mappedBy = "rendezVous")
	private Consultation consultation;
}
