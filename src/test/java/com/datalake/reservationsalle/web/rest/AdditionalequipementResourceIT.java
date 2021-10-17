package com.datalake.reservationsalle.web.rest;

import com.datalake.reservationsalle.SalleReservationApp;
import com.datalake.reservationsalle.domain.Additionalequipement;
import com.datalake.reservationsalle.repository.AdditionalequipementRepository;
import com.datalake.reservationsalle.service.AdditionalequipementService;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AdditionalequipementResource} REST controller.
 */
@SpringBootTest(classes = SalleReservationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class AdditionalequipementResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    @Autowired
    private AdditionalequipementRepository additionalequipementRepository;

    @Autowired
    private AdditionalequipementService additionalequipementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdditionalequipementMockMvc;

    private Additionalequipement additionalequipement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Additionalequipement createEntity(EntityManager em) {
        Additionalequipement additionalequipement = new Additionalequipement()
            .name(DEFAULT_NAME)
            .number(DEFAULT_NUMBER);
        return additionalequipement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Additionalequipement createUpdatedEntity(EntityManager em) {
        Additionalequipement additionalequipement = new Additionalequipement()
            .name(UPDATED_NAME)
            .number(UPDATED_NUMBER);
        return additionalequipement;
    }

    @BeforeEach
    public void initTest() {
        additionalequipement = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdditionalequipement() throws Exception {
        int databaseSizeBeforeCreate = additionalequipementRepository.findAll().size();

        // Create the Additionalequipement
        restAdditionalequipementMockMvc.perform(post("/api/additionalequipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(additionalequipement)))
            .andExpect(status().isCreated());

        // Validate the Additionalequipement in the database
        List<Additionalequipement> additionalequipementList = additionalequipementRepository.findAll();
        assertThat(additionalequipementList).hasSize(databaseSizeBeforeCreate + 1);
        Additionalequipement testAdditionalequipement = additionalequipementList.get(additionalequipementList.size() - 1);
        assertThat(testAdditionalequipement.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdditionalequipement.getNumber()).isEqualTo(DEFAULT_NUMBER);
    }

    @Test
    @Transactional
    public void createAdditionalequipementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = additionalequipementRepository.findAll().size();

        // Create the Additionalequipement with an existing ID
        additionalequipement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdditionalequipementMockMvc.perform(post("/api/additionalequipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(additionalequipement)))
            .andExpect(status().isBadRequest());

        // Validate the Additionalequipement in the database
        List<Additionalequipement> additionalequipementList = additionalequipementRepository.findAll();
        assertThat(additionalequipementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdditionalequipements() throws Exception {
        // Initialize the database
        additionalequipementRepository.saveAndFlush(additionalequipement);

        // Get all the additionalequipementList
        restAdditionalequipementMockMvc.perform(get("/api/additionalequipements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(additionalequipement.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getAdditionalequipement() throws Exception {
        // Initialize the database
        additionalequipementRepository.saveAndFlush(additionalequipement);

        // Get the additionalequipement
        restAdditionalequipementMockMvc.perform(get("/api/additionalequipements/{id}", additionalequipement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(additionalequipement.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER));
    }

    @Test
    @Transactional
    public void getNonExistingAdditionalequipement() throws Exception {
        // Get the additionalequipement
        restAdditionalequipementMockMvc.perform(get("/api/additionalequipements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdditionalequipement() throws Exception {
        // Initialize the database
        additionalequipementService.save(additionalequipement);

        int databaseSizeBeforeUpdate = additionalequipementRepository.findAll().size();

        // Update the additionalequipement
        Additionalequipement updatedAdditionalequipement = additionalequipementRepository.findById(additionalequipement.getId()).get();
        // Disconnect from session so that the updates on updatedAdditionalequipement are not directly saved in db
        em.detach(updatedAdditionalequipement);
        updatedAdditionalequipement
            .name(UPDATED_NAME)
            .number(UPDATED_NUMBER);

        restAdditionalequipementMockMvc.perform(put("/api/additionalequipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdditionalequipement)))
            .andExpect(status().isOk());

        // Validate the Additionalequipement in the database
        List<Additionalequipement> additionalequipementList = additionalequipementRepository.findAll();
        assertThat(additionalequipementList).hasSize(databaseSizeBeforeUpdate);
        Additionalequipement testAdditionalequipement = additionalequipementList.get(additionalequipementList.size() - 1);
        assertThat(testAdditionalequipement.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdditionalequipement.getNumber()).isEqualTo(UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingAdditionalequipement() throws Exception {
        int databaseSizeBeforeUpdate = additionalequipementRepository.findAll().size();

        // Create the Additionalequipement

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdditionalequipementMockMvc.perform(put("/api/additionalequipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(additionalequipement)))
            .andExpect(status().isBadRequest());

        // Validate the Additionalequipement in the database
        List<Additionalequipement> additionalequipementList = additionalequipementRepository.findAll();
        assertThat(additionalequipementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdditionalequipement() throws Exception {
        // Initialize the database
        additionalequipementService.save(additionalequipement);

        int databaseSizeBeforeDelete = additionalequipementRepository.findAll().size();

        // Delete the additionalequipement
        restAdditionalequipementMockMvc.perform(delete("/api/additionalequipements/{id}", additionalequipement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Additionalequipement> additionalequipementList = additionalequipementRepository.findAll();
        assertThat(additionalequipementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
