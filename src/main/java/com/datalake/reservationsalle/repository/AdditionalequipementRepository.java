package com.datalake.reservationsalle.repository;

import com.datalake.reservationsalle.domain.Additionalequipement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Additionalequipement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdditionalequipementRepository extends JpaRepository<Additionalequipement, Long> {
}
