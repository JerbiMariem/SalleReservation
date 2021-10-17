package com.datalake.reservationsalle.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A Equipementsreservation.
 */
@Entity
@Table(name = "equipementsreservation")
public class Equipementsreservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "datereservation")
    private LocalTime datereservation;

    @ManyToOne
    @JsonIgnoreProperties("equipementsreservations")
    private Additionalequipement additionalequipement;

    public Equipementsreservation(LocalTime datereservation, Additionalequipement additionalequipement) {
		this.datereservation= datereservation;
		this.additionalequipement= additionalequipement;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getDatereservation() {
        return datereservation;
    }

    public Equipementsreservation datereservation(LocalTime datereservation) {
        this.datereservation = datereservation;
        return this;
    }

    public void setDatereservation(LocalTime datereservation) {
        this.datereservation = datereservation;
    }

    public Additionalequipement getAdditionalequipement() {
        return additionalequipement;
    }

    public Equipementsreservation additionalequipement(Additionalequipement additionalequipement) {
        this.additionalequipement = additionalequipement;
        return this;
    }

    public void setAdditionalequipement(Additionalequipement additionalequipement) {
        this.additionalequipement = additionalequipement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Equipementsreservation)) {
            return false;
        }
        return id != null && id.equals(((Equipementsreservation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Equipementsreservation{" +
            "id=" + getId() +
            ", datereservation='" + getDatereservation() + "'" +
            "}";
    }
}
