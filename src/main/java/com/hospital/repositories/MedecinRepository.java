package com.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entities.Medecin;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {

}
