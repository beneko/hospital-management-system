package com.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entities.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

}
