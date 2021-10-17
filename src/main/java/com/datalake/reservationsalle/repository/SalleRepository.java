package com.datalake.reservationsalle.repository;

import com.datalake.reservationsalle.domain.Salle;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Salle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {
	
	@Query(value="select s from Salle s LEFT JOIN Sallereservation rs on rs.salle.id = s.id"
			+ " where rs.salle.id IS NULL and (s.capacity)*0.7>= capacity and rs.datereservation = (datereservation)")
	public List<Salle> findSalle (@Param("datereservation")LocalTime datereservation,@Param("capacity") int capacity);
	
}
