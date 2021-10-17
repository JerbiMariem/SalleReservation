package com.datalake.reservationsalle.web.rest;

import com.datalake.reservationsalle.SalleReservationApp;
import com.datalake.reservationsalle.domain.Equipements;
import com.datalake.reservationsalle.repository.EquipementsRepository;
import com.datalake.reservationsalle.service.EquipementsService;

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
 * Integration tests for the {@link EquipementsResource} REST controller.
 */
@SpringBootTest(classes = SalleReservationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class EquipementsResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private EquipementsRepository equipementsRepository;

    @Autowired
    private EquipementsService equipementsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEquipementsMockMvc;

    private Equipements equipements;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Equipements createEntity(EntityManager em) {
        Equipements equipements = new Equipements()
            .name(DEFAULT_NAME);
        return equipements;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Equipements createUpdatedEntity(EntityManager em) {
        Equipements equipements = new Equipements()
            .name(UPDATED_NAME);
        return equipements;
    }

    @BeforeEach
    public void initTest() {
        equipements = createEntity(em);
    }

    @Test
    @Transactional
    public void createEquipements() throws Exception {
        int databaseSizeBeforeCreate = equipementsRepository.findAll().size();

        // Create the Equipements
        restEquipementsMockMvc.perform(post("/api/equipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(equipements)))
            .andExpect(status().isCreated());

        // Validate the Equipements in the database
        List<Equipements> equipementsList = equipementsRepository.findAll();
        assertThat(equipementsList).hasSize(databaseSizeBeforeCreate + 1);
        Equipements testEquipements = equipementsList.get(equipementsList.size() - 1);
        assertThat(testEquipements.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createEquipementsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = equipementsRepository.findAll().size();

        // Create the Equipements with an existing ID
        equipements.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEquipementsMockMvc.perform(post("/api/equipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(equipements)))
            .andExpect(status().isBadRequest());

        // Validate the Equipements in the database
        List<Equipements> equipementsList = equipementsRepository.findAll();
        assertThat(equipementsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEquipements() throws Exception {
        // Initialize the database
        equipementsRepository.saveAndFlush(equipements);

        // Get all the equipementsList
        restEquipementsMockMvc.perform(get("/api/equipements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(equipements.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getEquipements() throws Exception {
        // Initialize the database
        equipementsRepository.saveAndFlush(equipements);

        // Get the equipements
        restEquipementsMockMvc.perform(get("/api/equipements/{id}", equipements.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(equipements.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingEquipements() throws Exception {
        // Get the equipements
        restEquipementsMockMvc.perform(get("/api/equipements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEquipements() throws Exception {
        // Initialize the database
        equipementsService.save(equipements);

        int databaseSizeBeforeUpdate = equipementsRepository.findAll().size();

        // Update the equipements
        Equipements updatedEquipements = equipementsRepository.findById(equipements.getId()).get();
        // Disconnect from session so that the updates on updatedEquipements are not directly saved in db
        em.detach(updatedEquipements);
        updatedEquipements
            .name(UPDATED_NAME);

        restEquipementsMockMvc.perform(put("/api/equipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEquipements)))
            .andExpect(status().isOk());

        // Validate the Equipements in the database
        List<Equipements> equipementsList = equipementsRepository.findAll();
        assertThat(equipementsList).hasSize(databaseSizeBeforeUpdate);
        Equipements testEquipements = equipementsList.get(equipementsList.size() - 1);
        assertThat(testEquipements.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingEquipements() throws Exception {
        int databaseSizeBeforeUpdate = equipementsRepository.findAll().size();

        // Create the Equipements

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEquipementsMockMvc.perform(put("/api/equipements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(equipements)))
            .andExpect(status().isBadRequest());

        // Validate the Equipements in the database
        List<Equipements> equipementsList = equipementsRepository.findAll();
        assertThat(equipementsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEquipements() throws Exception {
        // Initialize the database
        equipementsService.save(equipements);

        int databaseSizeBeforeDelete = equipementsRepository.findAll().size();

        // Delete the equipements
        restEquipementsMockMvc.perform(delete("/api/equipements/{id}", equipements.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Equipements> equipementsList = equipementsRepository.findAll();
        assertThat(equipementsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
