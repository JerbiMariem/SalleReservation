package com.datalake.reservationsalle.web.rest;

import com.datalake.reservationsalle.domain.Sallereservation;
import com.datalake.reservationsalle.service.SallereservationService;
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
 * REST controller for managing {@link com.datalake.reservationsalle.domain.Sallereservation}.
 */
@RestController
@RequestMapping("/api")
public class SallereservationResource {

    private final Logger log = LoggerFactory.getLogger(SallereservationResource.class);

    private static final String ENTITY_NAME = "sallereservation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SallereservationService sallereservationService;

    public SallereservationResource(SallereservationService sallereservationService) {
        this.sallereservationService = sallereservationService;
    }

    /**
     * {@code POST  /sallereservations} : Create a new sallereservation.
     *
     * @param sallereservation the sallereservation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sallereservation, or with status {@code 400 (Bad Request)} if the sallereservation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sallereservations")
    public ResponseEntity<Sallereservation> createSallereservation(@RequestBody Sallereservation sallereservation) throws URISyntaxException {
        log.debug("REST request to save Sallereservation : {}", sallereservation);
        if (sallereservation.getId() != null) {
            throw new BadRequestAlertException("A new sallereservation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Sallereservation result = sallereservationService.save(sallereservation);
        return ResponseEntity.created(new URI("/api/sallereservations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sallereservations} : Updates an existing sallereservation.
     *
     * @param sallereservation the sallereservation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sallereservation,
     * or with status {@code 400 (Bad Request)} if the sallereservation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sallereservation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sallereservations")
    public ResponseEntity<Sallereservation> updateSallereservation(@RequestBody Sallereservation sallereservation) throws URISyntaxException {
        log.debug("REST request to update Sallereservation : {}", sallereservation);
        if (sallereservation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Sallereservation result = sallereservationService.save(sallereservation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sallereservation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sallereservations} : get all the sallereservations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sallereservations in body.
     */
    @GetMapping("/sallereservations")
    public List<Sallereservation> getAllSallereservations() {
        log.debug("REST request to get all Sallereservations");
        return sallereservationService.findAll();
    }

    /**
     * {@code GET  /sallereservations/:id} : get the "id" sallereservation.
     *
     * @param id the id of the sallereservation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sallereservation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sallereservations/{id}")
    public ResponseEntity<Sallereservation> getSallereservation(@PathVariable Long id) {
        log.debug("REST request to get Sallereservation : {}", id);
        Optional<Sallereservation> sallereservation = sallereservationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sallereservation);
    }

    /**
     * {@code DELETE  /sallereservations/:id} : delete the "id" sallereservation.
     *
     * @param id the id of the sallereservation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sallereservations/{id}")
    public ResponseEntity<Void> deleteSallereservation(@PathVariable Long id) {
        log.debug("REST request to delete Sallereservation : {}", id);
        sallereservationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
