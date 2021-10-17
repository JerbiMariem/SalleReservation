package com.datalake.reservationsalle.web.rest;

import com.datalake.reservationsalle.domain.Equipementsreservation;
import com.datalake.reservationsalle.service.EquipementsreservationService;
import com.datalake.reservationsalle.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.datalake.reservationsalle.domain.Equipementsreservation}.
 */
@RestController
@RequestMapping("/api")
public class EquipementsreservationResource {

    private final Logger log = LoggerFactory.getLogger(EquipementsreservationResource.class);

    private static final String ENTITY_NAME = "equipementsreservation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EquipementsreservationService equipementsreservationService;

    public EquipementsreservationResource(EquipementsreservationService equipementsreservationService) {
        this.equipementsreservationService = equipementsreservationService;
    }

    /**
     * {@code POST  /equipementsreservations} : Create a new equipementsreservation.
     *
     * @param equipementsreservation the equipementsreservation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new equipementsreservation, or with status {@code 400 (Bad Request)} if the equipementsreservation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/equipementsreservations")
    public ResponseEntity<Equipementsreservation> createEquipementsreservation(@RequestBody Equipementsreservation equipementsreservation) throws URISyntaxException {
        log.debug("REST request to save Equipementsreservation : {}", equipementsreservation);
        if (equipementsreservation.getId() != null) {
            throw new BadRequestAlertException("A new equipementsreservation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Equipementsreservation result = equipementsreservationService.save(equipementsreservation);
        return ResponseEntity.created(new URI("/api/equipementsreservations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /equipementsreservations} : Updates an existing equipementsreservation.
     *
     * @param equipementsreservation the equipementsreservation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated equipementsreservation,
     * or with status {@code 400 (Bad Request)} if the equipementsreservation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the equipementsreservation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/equipementsreservations")
    public ResponseEntity<Equipementsreservation> updateEquipementsreservation(@RequestBody Equipementsreservation equipementsreservation) throws URISyntaxException {
        log.debug("REST request to update Equipementsreservation : {}", equipementsreservation);
        if (equipementsreservation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Equipementsreservation result = equipementsreservationService.save(equipementsreservation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, equipementsreservation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /equipementsreservations} : get all the equipementsreservations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of equipementsreservations in body.
     */
    @GetMapping("/equipementsreservations")
    public List<Equipementsreservation> getAllEquipementsreservations() {
        log.debug("REST request to get all Equipementsreservations");
        return equipementsreservationService.findAll();
    }

    /**
     * {@code GET  /equipementsreservations/:id} : get the "id" equipementsreservation.
     *
     * @param id the id of the equipementsreservation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the equipementsreservation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/equipementsreservations/{id}")
    public ResponseEntity<Equipementsreservation> getEquipementsreservation(@PathVariable Long id) {
        log.debug("REST request to get Equipementsreservation : {}", id);
        Optional<Equipementsreservation> equipementsreservation = equipementsreservationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(equipementsreservation);
    }

    /**
     * {@code DELETE  /equipementsreservations/:id} : delete the "id" equipementsreservation.
     *
     * @param id the id of the equipementsreservation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/equipementsreservations/{id}")
    public ResponseEntity<Void> deleteEquipementsreservation(@PathVariable Long id) {
        log.debug("REST request to delete Equipementsreservation : {}", id);
        equipementsreservationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
