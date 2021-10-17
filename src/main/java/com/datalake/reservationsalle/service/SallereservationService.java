package com.datalake.reservationsalle.service;

import com.datalake.reservationsalle.domain.Sallereservation;
import com.datalake.reservationsalle.repository.SallereservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Sallereservation}.
 */
@Service
@Transactional
public class SallereservationService {

    private final Logger log = LoggerFactory.getLogger(SallereservationService.class);

    private final SallereservationRepository sallereservationRepository;

    public SallereservationService(SallereservationRepository sallereservationRepository) {
        this.sallereservationRepository = sallereservationRepository;
    }

    /**
     * Save a sallereservation.
     *
     * @param sallereservation the entity to save.
     * @return the persisted entity.
     */
    public Sallereservation save(Sallereservation sallereservation) {
        log.debug("Request to save Sallereservation : {}", sallereservation);
        return sallereservationRepository.save(sallereservation);
    }

    /**
     * Get all the sallereservations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Sallereservation> findAll() {
        log.debug("Request to get all Sallereservations");
        return sallereservationRepository.findAll();
    }

    /**
     * Get one sallereservation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Sallereservation> findOne(Long id) {
        log.debug("Request to get Sallereservation : {}", id);
        return sallereservationRepository.findById(id);
    }

    /**
     * Delete the sallereservation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Sallereservation : {}", id);
        sallereservationRepository.deleteById(id);
    }
}
