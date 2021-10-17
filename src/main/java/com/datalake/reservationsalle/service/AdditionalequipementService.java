package com.datalake.reservationsalle.service;

import com.datalake.reservationsalle.domain.Additionalequipement;
import com.datalake.reservationsalle.repository.AdditionalequipementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Additionalequipement}.
 */
@Service
@Transactional
public class AdditionalequipementService {

    private final Logger log = LoggerFactory.getLogger(AdditionalequipementService.class);

    private final AdditionalequipementRepository additionalequipementRepository;

    public AdditionalequipementService(AdditionalequipementRepository additionalequipementRepository) {
        this.additionalequipementRepository = additionalequipementRepository;
    }

    /**
     * Save a additionalequipement.
     *
     * @param additionalequipement the entity to save.
     * @return the persisted entity.
     */
    public Additionalequipement save(Additionalequipement additionalequipement) {
        log.debug("Request to save Additionalequipement : {}", additionalequipement);
        return additionalequipementRepository.save(additionalequipement);
    }

    /**
     * Get all the additionalequipements.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Additionalequipement> findAll() {
        log.debug("Request to get all Additionalequipements");
        return additionalequipementRepository.findAll();
    }

    /**
     * Get one additionalequipement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Additionalequipement> findOne(Long id) {
        log.debug("Request to get Additionalequipement : {}", id);
        return additionalequipementRepository.findById(id);
    }

    /**
     * Delete the additionalequipement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Additionalequipement : {}", id);
        additionalequipementRepository.deleteById(id);
    }
}
