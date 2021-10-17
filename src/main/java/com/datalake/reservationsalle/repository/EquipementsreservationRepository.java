package com.datalake.reservationsalle.repository;

import com.datalake.reservationsalle.domain.Equipementsreservation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Equipementsreservation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EquipementsreservationRepository extends JpaRepository<Equipementsreservation, Long> {
}
