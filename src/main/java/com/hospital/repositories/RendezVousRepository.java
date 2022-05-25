package com.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entities.RendezVous;

public interface RendezVousRepository extends JpaRepository<RendezVous, String> {

}
