package com.datalake.reservationsalle.service;

import com.datalake.reservationsalle.domain.Equipementsreservation;
import com.datalake.reservationsalle.repository.EquipementsreservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Equipementsreservation}.
 */
@Service
@Transactional
public class EquipementsreservationService {

    private final Logger log = LoggerFactory.getLogger(EquipementsreservationService.class);

    private final EquipementsreservationRepository equipementsreservationRepository;

    public EquipementsreservationService(EquipementsreservationRepository equipementsreservationRepository) {
        this.equipementsreservationRepository = equipementsreservationRepository;
    }

    /**
     * Save a equipementsreservation.
     *
     * @param equipementsreservation the entity to save.
     * @return the persisted entity.
     */
    public Equipementsreservation save(Equipementsreservation equipementsreservation) {
        log.debug("Request to save Equipementsreservation : {}", equipementsreservation);
        return equipementsreservationRepository.save(equipementsreservation);
    }

    /**
     * Get all the equipementsreservations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Equipementsreservation> findAll() {
        log.debug("Request to get all Equipementsreservations");
        return equipementsreservationRepository.findAll();
    }

    /**
     * Get one equipementsreservation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Equipementsreservation> findOne(Long id) {
        log.debug("Request to get Equipementsreservation : {}", id);
        return equipementsreservationRepository.findById(id);
    }

    /**
     * Delete the equipementsreservation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Equipementsreservation : {}", id);
        equipementsreservationRepository.deleteById(id);
    }
}
