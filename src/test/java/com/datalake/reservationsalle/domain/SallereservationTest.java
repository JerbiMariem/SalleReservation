package com.datalake.reservationsalle.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.datalake.reservationsalle.web.rest.TestUtil;

public class SallereservationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sallereservation.class);
        Sallereservation sallereservation1 = new Sallereservation(null, null);
        sallereservation1.setId(1L);
        Sallereservation sallereservation2 = new Sallereservation(null, null);
        sallereservation2.setId(sallereservation1.getId());
        assertThat(sallereservation1).isEqualTo(sallereservation2);
        sallereservation2.setId(2L);
        assertThat(sallereservation1).isNotEqualTo(sallereservation2);
        sallereservation1.setId(null);
        assertThat(sallereservation1).isNotEqualTo(sallereservation2);
    }
}
