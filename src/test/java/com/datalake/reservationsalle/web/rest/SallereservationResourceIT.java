package com.datalake.reservationsalle.web.rest;

import com.datalake.reservationsalle.SalleReservationApp;
import com.datalake.reservationsalle.domain.Sallereservation;
import com.datalake.reservationsalle.repository.SallereservationRepository;
import com.datalake.reservationsalle.service.SallereservationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SallereservationResource} REST controller.
 */
@SpringBootTest(classes = SalleReservationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class SallereservationResourceIT {

    private static final LocalTime DEFAULT_DATERESERVATION = LocalTime.of(0,0);
    private static final LocalTime UPDATED_DATERESERVATION = LocalTime.now(ZoneId.systemDefault());

    @Autowired
    private SallereservationRepository sallereservationRepository;

    @Autowired
    private SallereservationService sallereservationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSallereservationMockMvc;

    private Sallereservation sallereservation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sallereservation createEntity(EntityManager em) {
        Sallereservation sallereservation = new Sallereservation(null, null)
            .datereservation(DEFAULT_DATERESERVATION);
        return sallereservation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sallereservation createUpdatedEntity(EntityManager em) {
        Sallereservation sallereservation = new Sallereservation(null, null)
            .datereservation(UPDATED_DATERESERVATION);
        return sallereservation;
    }

    @BeforeEach
    public void initTest() {
        sallereservation = createEntity(em);
    }

    @Test
    @Transactional
    public void createSallereservation() throws Exception {
        int databaseSizeBeforeCreate = sallereservationRepository.findAll().size();

        // Create the Sallereservation
        restSallereservationMockMvc.perform(post("/api/sallereservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sallereservation)))
            .andExpect(status().isCreated());

        // Validate the Sallereservation in the database
        List<Sallereservation> sallereservationList = sallereservationRepository.findAll();
        assertThat(sallereservationList).hasSize(databaseSizeBeforeCreate + 1);
        Sallereservation testSallereservation = sallereservationList.get(sallereservationList.size() - 1);
        assertThat(testSallereservation.getDatereservation()).isEqualTo(DEFAULT_DATERESERVATION);
    }

    @Test
    @Transactional
    public void createSallereservationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sallereservationRepository.findAll().size();

        // Create the Sallereservation with an existing ID
        sallereservation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSallereservationMockMvc.perform(post("/api/sallereservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sallereservation)))
            .andExpect(status().isBadRequest());

        // Validate the Sallereservation in the database
        List<Sallereservation> sallereservationList = sallereservationRepository.findAll();
        assertThat(sallereservationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSallereservations() throws Exception {
        // Initialize the database
        sallereservationRepository.saveAndFlush(sallereservation);

        // Get all the sallereservationList
        restSallereservationMockMvc.perform(get("/api/sallereservations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sallereservation.getId().intValue())))
            .andExpect(jsonPath("$.[*].datereservation").value(hasItem(DEFAULT_DATERESERVATION.toString())));
    }
    
    @Test
    @Transactional
    public void getSallereservation() throws Exception {
        // Initialize the database
        sallereservationRepository.saveAndFlush(sallereservation);

        // Get the sallereservation
        restSallereservationMockMvc.perform(get("/api/sallereservations/{id}", sallereservation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sallereservation.getId().intValue()))
            .andExpect(jsonPath("$.datereservation").value(DEFAULT_DATERESERVATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSallereservation() throws Exception {
        // Get the sallereservation
        restSallereservationMockMvc.perform(get("/api/sallereservations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSallereservation() throws Exception {
        // Initialize the database
        sallereservationService.save(sallereservation);

        int databaseSizeBeforeUpdate = sallereservationRepository.findAll().size();

        // Update the sallereservation
        Sallereservation updatedSallereservation = sallereservationRepository.findById(sallereservation.getId()).get();
        // Disconnect from session so that the updates on updatedSallereservation are not directly saved in db
        em.detach(updatedSallereservation);
        updatedSallereservation
            .datereservation(UPDATED_DATERESERVATION);

        restSallereservationMockMvc.perform(put("/api/sallereservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSallereservation)))
            .andExpect(status().isOk());

        // Validate the Sallereservation in the database
        List<Sallereservation> sallereservationList = sallereservationRepository.findAll();
        assertThat(sallereservationList).hasSize(databaseSizeBeforeUpdate);
        Sallereservation testSallereservation = sallereservationList.get(sallereservationList.size() - 1);
        assertThat(testSallereservation.getDatereservation()).isEqualTo(UPDATED_DATERESERVATION);
    }

    @Test
    @Transactional
    public void updateNonExistingSallereservation() throws Exception {
        int databaseSizeBeforeUpdate = sallereservationRepository.findAll().size();

        // Create the Sallereservation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSallereservationMockMvc.perform(put("/api/sallereservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sallereservation)))
            .andExpect(status().isBadRequest());

        // Validate the Sallereservation in the database
        List<Sallereservation> sallereservationList = sallereservationRepository.findAll();
        assertThat(sallereservationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSallereservation() throws Exception {
        // Initialize the database
        sallereservationService.save(sallereservation);

        int databaseSizeBeforeDelete = sallereservationRepository.findAll().size();

        // Delete the sallereservation
        restSallereservationMockMvc.perform(delete("/api/sallereservations/{id}", sallereservation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sallereservation> sallereservationList = sallereservationRepository.findAll();
        assertThat(sallereservationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
