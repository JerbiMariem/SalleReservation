package com.datalake.reservationsalle.service;

import com.datalake.reservationsalle.domain.Equipements;
import com.datalake.reservationsalle.repository.EquipementsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Equipements}.
 */
@Service
@Transactional
public class EquipementsService {

    private final Logger log = LoggerFactory.getLogger(EquipementsService.class);

    private final EquipementsRepository equipementsRepository;

    public EquipementsService(EquipementsRepository equipementsRepository) {
        this.equipementsRepository = equipementsRepository;
    }

    /**
     * Save a equipements.
     *
     * @param equipements the entity to save.
     * @return the persisted entity.
     */
    public Equipements save(Equipements equipements) {
        log.debug("Request to save Equipements : {}", equipements);
        return equipementsRepository.save(equipements);
    }

    /**
     * Get all the equipements.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Equipements> findAll() {
        log.debug("Request to get all Equipements");
        return equipementsRepository.findAll();
    }

    /**
     * Get one equipements by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Equipements> findOne(Long id) {
        log.debug("Request to get Equipements : {}", id);
        return equipementsRepository.findById(id);
    }

    /**
     * Delete the equipements by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Equipements : {}", id);
        equipementsRepository.deleteById(id);
    }
}
