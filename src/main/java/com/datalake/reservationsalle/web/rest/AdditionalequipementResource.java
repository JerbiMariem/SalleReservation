package com.datalake.reservationsalle.web.rest;

import com.datalake.reservationsalle.domain.Additionalequipement;
import com.datalake.reservationsalle.service.AdditionalequipementService;
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
 * REST controller for managing {@link com.datalake.reservationsalle.domain.Additionalequipement}.
 */
@RestController
@RequestMapping("/api")
public class AdditionalequipementResource {

    private final Logger log = LoggerFactory.getLogger(AdditionalequipementResource.class);

    private static final String ENTITY_NAME = "additionalequipement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdditionalequipementService additionalequipementService;

    public AdditionalequipementResource(AdditionalequipementService additionalequipementService) {
        this.additionalequipementService = additionalequipementService;
    }

    /**
     * {@code POST  /additionalequipements} : Create a new additionalequipement.
     *
     * @param additionalequipement the additionalequipement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new additionalequipement, or with status {@code 400 (Bad Request)} if the additionalequipement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/additionalequipements")
    public ResponseEntity<Additionalequipement> createAdditionalequipement(@RequestBody Additionalequipement additionalequipement) throws URISyntaxException {
        log.debug("REST request to save Additionalequipement : {}", additionalequipement);
        if (additionalequipement.getId() != null) {
            throw new BadRequestAlertException("A new additionalequipement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Additionalequipement result = additionalequipementService.save(additionalequipement);
        return ResponseEntity.created(new URI("/api/additionalequipements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /additionalequipements} : Updates an existing additionalequipement.
     *
     * @param additionalequipement the additionalequipement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated additionalequipement,
     * or with status {@code 400 (Bad Request)} if the additionalequipement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the additionalequipement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/additionalequipements")
    public ResponseEntity<Additionalequipement> updateAdditionalequipement(@RequestBody Additionalequipement additionalequipement) throws URISyntaxException {
        log.debug("REST request to update Additionalequipement : {}", additionalequipement);
        if (additionalequipement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Additionalequipement result = additionalequipementService.save(additionalequipement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, additionalequipement.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /additionalequipements} : get all the additionalequipements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of additionalequipements in body.
     */
    @GetMapping("/additionalequipements")
    public List<Additionalequipement> getAllAdditionalequipements() {
        log.debug("REST request to get all Additionalequipements");
        return additionalequipementService.findAll();
    }

    /**
     * {@code GET  /additionalequipements/:id} : get the "id" additionalequipement.
     *
     * @param id the id of the additionalequipement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the additionalequipement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/additionalequipements/{id}")
    public ResponseEntity<Additionalequipement> getAdditionalequipement(@PathVariable Long id) {
        log.debug("REST request to get Additionalequipement : {}", id);
        Optional<Additionalequipement> additionalequipement = additionalequipementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(additionalequipement);
    }

    /**
     * {@code DELETE  /additionalequipements/:id} : delete the "id" additionalequipement.
     *
     * @param id the id of the additionalequipement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/additionalequipements/{id}")
    public ResponseEntity<Void> deleteAdditionalequipement(@PathVariable Long id) {
        log.debug("REST request to delete Additionalequipement : {}", id);
        additionalequipementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
