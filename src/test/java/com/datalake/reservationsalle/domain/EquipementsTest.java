package com.datalake.reservationsalle.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datalake.reservationsalle.web.rest.TestUtil;

public class EquipementsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Equipements.class);
        Equipements equipements1 = new Equipements();
        equipements1.setId(1L);
        Equipements equipements2 = new Equipements();
        equipements2.setId(equipements1.getId());
        assertThat(equipements1).isEqualTo(equipements2);
        equipements2.setId(2L);
        assertThat(equipements1).isNotEqualTo(equipements2);
        equipements1.setId(null);
        assertThat(equipements1).isNotEqualTo(equipements2);
    }
}
