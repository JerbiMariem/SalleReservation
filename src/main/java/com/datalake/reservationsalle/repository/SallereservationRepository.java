package com.datalake.reservationsalle.repository;

import com.datalake.reservationsalle.domain.Sallereservation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Sallereservation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SallereservationRepository extends JpaRepository<Sallereservation, Long> {
}
