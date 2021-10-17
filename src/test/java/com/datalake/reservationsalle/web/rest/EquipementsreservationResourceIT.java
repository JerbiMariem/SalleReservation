package com.datalake.reservationsalle.web.rest;

import com.datalake.reservationsalle.SalleReservationApp;
import com.datalake.reservationsalle.domain.Equipementsreservation;
import com.datalake.reservationsalle.repository.EquipementsreservationRepository;
import com.datalake.reservationsalle.service.EquipementsreservationService;

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
 * Integration tests for the {@link EquipementsreservationResource} REST controller.
 */
@SpringBootTest(classes = SalleReservationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class EquipementsreservationResourceIT {

    private static final LocalTime DEFAULT_DATERESERVATION = LocalTime.of(0, 0);
    private static final LocalTime UPDATED_DATERESERVATION = LocalTime.now(ZoneId.systemDefault());

    @Autowired
    private EquipementsreservationRepository equipementsreservationRepository;

    @Autowired
    private EquipementsreservationService equipementsreservationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEquipementsreservationMockMvc;

    private Equipementsreservation equipementsreservation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Equipementsreservation createEntity(EntityManager em) {
        Equipementsreservation equipementsreservation = new Equipementsreservation(null, null)
            .datereservation(DEFAULT_DATERESERVATION);
        return equipementsreservation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Equipementsreservation createUpdatedEntity(EntityManager em) {
        Equipementsreservation equipementsreservation = new Equipementsreservation(null, null)
            .datereservation(UPDATED_DATERESERVATION);
        return equipementsreservation;
    }

    @BeforeEach
    public void initTest() {
        equipementsreservation = createEntity(em);
    }

    @Test
    @Transactional
    public void createEquipementsreservation() throws Exception {
        int databaseSizeBeforeCreate = equipementsreservationRepository.findAll().size();

        // Create the Equipementsreservation
        restEquipementsreservationMockMvc.perform(post("/api/equipementsreservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(equipementsreservation)))
            .andExpect(status().isCreated());

        // Validate the Equipementsreservation in the database
        List<Equipementsreservation> equipementsreservationList = equipementsreservationRepository.findAll();
        assertThat(equipementsreservationList).hasSize(databaseSizeBeforeCreate + 1);
        Equipementsreservation testEquipementsreservation = equipementsreservationList.get(equipementsreservationList.size() - 1);
        assertThat(testEquipementsreservation.getDatereservation()).isEqualTo(DEFAULT_DATERESERVATION);
    }

    @Test
    @Transactional
    public void createEquipementsreservationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = equipementsreservationRepository.findAll().size();

        // Create the Equipementsreservation with an existing ID
        equipementsreservation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEquipementsreservationMockMvc.perform(post("/api/equipementsreservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(equipementsreservation)))
            .andExpect(status().isBadRequest());

        // Validate the Equipementsreservation in the database
        List<Equipementsreservation> equipementsreservationList = equipementsreservationRepository.findAll();
        assertThat(equipementsreservationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEquipementsreservations() throws Exception {
        // Initialize the database
        equipementsreservationRepository.saveAndFlush(equipementsreservation);

        // Get all the equipementsreservationList
        restEquipementsreservationMockMvc.perform(get("/api/equipementsreservations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(equipementsreservation.getId().intValue())))
            .andExpect(jsonPath("$.[*].datereservation").value(hasItem(DEFAULT_DATERESERVATION.toString())));
    }
    
    @Test
    @Transactional
    public void getEquipementsreservation() throws Exception {
        // Initialize the database
        equipementsreservationRepository.saveAndFlush(equipementsreservation);

        // Get the equipementsreservation
        restEquipementsreservationMockMvc.perform(get("/api/equipementsreservations/{id}", equipementsreservation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(equipementsreservation.getId().intValue()))
            .andExpect(jsonPath("$.datereservation").value(DEFAULT_DATERESERVATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEquipementsreservation() throws Exception {
        // Get the equipementsreservation
        restEquipementsreservationMockMvc.perform(get("/api/equipementsreservations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEquipementsreservation() throws Exception {
        // Initialize the database
        equipementsreservationService.save(equipementsreservation);

        int databaseSizeBeforeUpdate = equipementsreservationRepository.findAll().size();

        // Update the equipementsreservation
        Equipementsreservation updatedEquipementsreservation = equipementsreservationRepository.findById(equipementsreservation.getId()).get();
        // Disconnect from session so that the updates on updatedEquipementsreservation are not directly saved in db
        em.detach(updatedEquipementsreservation);
        updatedEquipementsreservation
            .datereservation(UPDATED_DATERESERVATION);

        restEquipementsreservationMockMvc.perform(put("/api/equipementsreservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEquipementsreservation)))
            .andExpect(status().isOk());

        // Validate the Equipementsreservation in the database
        List<Equipementsreservation> equipementsreservationList = equipementsreservationRepository.findAll();
        assertThat(equipementsreservationList).hasSize(databaseSizeBeforeUpdate);
        Equipementsreservation testEquipementsreservation = equipementsreservationList.get(equipementsreservationList.size() - 1);
        assertThat(testEquipementsreservation.getDatereservation()).isEqualTo(UPDATED_DATERESERVATION);
    }

    @Test
    @Transactional
    public void updateNonExistingEquipementsreservation() throws Exception {
        int databaseSizeBeforeUpdate = equipementsreservationRepository.findAll().size();

        // Create the Equipementsreservation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEquipementsreservationMockMvc.perform(put("/api/equipementsreservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(equipementsreservation)))
            .andExpect(status().isBadRequest());

        // Validate the Equipementsreservation in the database
        List<Equipementsreservation> equipementsreservationList = equipementsreservationRepository.findAll();
        assertThat(equipementsreservationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEquipementsreservation() throws Exception {
        // Initialize the database
        equipementsreservationService.save(equipementsreservation);

        int databaseSizeBeforeDelete = equipementsreservationRepository.findAll().size();

        // Delete the equipementsreservation
        restEquipementsreservationMockMvc.perform(delete("/api/equipementsreservations/{id}", equipementsreservation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Equipementsreservation> equipementsreservationList = equipementsreservationRepository.findAll();
        assertThat(equipementsreservationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
