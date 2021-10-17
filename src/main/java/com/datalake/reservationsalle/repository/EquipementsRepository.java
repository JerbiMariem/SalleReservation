package com.datalake.reservationsalle.repository;

import com.datalake.reservationsalle.domain.Equipements;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Equipements entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EquipementsRepository extends JpaRepository<Equipements, Long> {
}
