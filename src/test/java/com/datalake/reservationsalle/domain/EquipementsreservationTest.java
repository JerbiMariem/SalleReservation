package com.datalake.reservationsalle.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datalake.reservationsalle.web.rest.TestUtil;

public class EquipementsreservationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Equipementsreservation.class);
        Equipementsreservation equipementsreservation1 = new Equipementsreservation(null, null);
        equipementsreservation1.setId(1L);
        Equipementsreservation equipementsreservation2 = new Equipementsreservation(null, null);
        equipementsreservation2.setId(equipementsreservation1.getId());
        assertThat(equipementsreservation1).isEqualTo(equipementsreservation2);
        equipementsreservation2.setId(2L);
        assertThat(equipementsreservation1).isNotEqualTo(equipementsreservation2);
        equipementsreservation1.setId(null);
        assertThat(equipementsreservation1).isNotEqualTo(equipementsreservation2);
    }
}
