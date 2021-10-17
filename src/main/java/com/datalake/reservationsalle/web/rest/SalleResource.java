package com.datalake.reservationsalle.web.rest;

import com.datalake.reservationsalle.domain.Salle;

import com.datalake.reservationsalle.repository.SalleRepository;
import com.datalake.reservationsalle.web.rest.errors.BadRequestAlertException;
import com.datalake.reservationsalle.service.SalleService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

/**
 * REST controller for managing {@link com.datalake.reservationsalle.domain.Salle}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SalleResource {

    private final Logger log = LoggerFactory.getLogger(SalleResource.class);

    private static final String ENTITY_NAME = "salle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalleRepository salleRepository;
    public  final SalleService salleService;
    

    public SalleResource(SalleRepository salleRepository, SalleService salleService) {
        this.salleRepository = salleRepository;
        this.salleService = salleService;
    }

    /**
     * {@code POST  /salles} : return salles.
     *
     * @param 
     * @return 
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    
   @GetMapping("/salles")
   public ResponseEntity<Salle> getSAlles(int capacity, String meetingType, LocalTime reservationDate ) throws URISyntaxException {
	   
	   return ResponseEntity.ok().body(salleService.findfinalsalle(capacity, meetingType, reservationDate));
	    
	   
   }
   
}
