package com.datalake.reservationsalle.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datalake.reservationsalle.web.rest.TestUtil;

public class AdditionalequipementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Additionalequipement.class);
        Additionalequipement additionalequipement1 = new Additionalequipement();
        additionalequipement1.setId(1L);
        Additionalequipement additionalequipement2 = new Additionalequipement();
        additionalequipement2.setId(additionalequipement1.getId());
        assertThat(additionalequipement1).isEqualTo(additionalequipement2);
        additionalequipement2.setId(2L);
        assertThat(additionalequipement1).isNotEqualTo(additionalequipement2);
        additionalequipement1.setId(null);
        assertThat(additionalequipement1).isNotEqualTo(additionalequipement2);
    }
}
