package com.datalake.reservationsalle.web.rest;

import com.datalake.reservationsalle.domain.Equipements;
import com.datalake.reservationsalle.service.EquipementsService;
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
 * REST controller for managing {@link com.datalake.reservationsalle.domain.Equipements}.
 */
@RestController
@RequestMapping("/api")
public class EquipementsResource {

    private final Logger log = LoggerFactory.getLogger(EquipementsResource.class);

    private static final String ENTITY_NAME = "equipements";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EquipementsService equipementsService;

    public EquipementsResource(EquipementsService equipementsService) {
        this.equipementsService = equipementsService;
    }

    /**
     * {@code POST  /equipements} : Create a new equipements.
     *
     * @param equipements the equipements to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new equipements, or with status {@code 400 (Bad Request)} if the equipements has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/equipements")
    public ResponseEntity<Equipements> createEquipements(@RequestBody Equipements equipements) throws URISyntaxException {
        log.debug("REST request to save Equipements : {}", equipements);
        if (equipements.getId() != null) {
            throw new BadRequestAlertException("A new equipements cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Equipements result = equipementsService.save(equipements);
        return ResponseEntity.created(new URI("/api/equipements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /equipements} : Updates an existing equipements.
     *
     * @param equipements the equipements to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated equipements,
     * or with status {@code 400 (Bad Request)} if the equipements is not valid,
     * or with status {@code 500 (Internal Server Error)} if the equipements couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/equipements")
    public ResponseEntity<Equipements> updateEquipements(@RequestBody Equipements equipements) throws URISyntaxException {
        log.debug("REST request to update Equipements : {}", equipements);
        if (equipements.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Equipements result = equipementsService.save(equipements);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, equipements.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /equipements} : get all the equipements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of equipements in body.
     */
    @GetMapping("/equipements")
    public List<Equipements> getAllEquipements() {
        log.debug("REST request to get all Equipements");
        return equipementsService.findAll();
    }

    /**
     * {@code GET  /equipements/:id} : get the "id" equipements.
     *
     * @param id the id of the equipements to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the equipements, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/equipements/{id}")
    public ResponseEntity<Equipements> getEquipements(@PathVariable Long id) {
        log.debug("REST request to get Equipements : {}", id);
        Optional<Equipements> equipements = equipementsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(equipements);
    }

    /**
     * {@code DELETE  /equipements/:id} : delete the "id" equipements.
     *
     * @param id the id of the equipements to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/equipements/{id}")
    public ResponseEntity<Void> deleteEquipements(@PathVariable Long id) {
        log.debug("REST request to delete Equipements : {}", id);
        equipementsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
