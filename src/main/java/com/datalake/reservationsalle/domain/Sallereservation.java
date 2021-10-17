package com.datalake.reservationsalle.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A Sallereservation.
 */
@Entity
@Table(name = "sallereservation")
public class Sallereservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "datereservation")
    private LocalTime datereservation;

    @ManyToOne
    @JsonIgnoreProperties("sallereservations")
    private Salle salle;

    public Sallereservation(LocalTime datereservation, Salle salle) {
    	this.datereservation = datereservation;
    	this.salle = salle;
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

    public Sallereservation datereservation(LocalTime datereservation) {
        this.datereservation = datereservation;
        return this;
    }

    public void setDatereservation(LocalTime datereservation) {
        this.datereservation = datereservation;
    }

    public Salle getSalle() {
        return salle;
    }

    public Sallereservation salle(Salle salle) {
        this.salle = salle;
        return this;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sallereservation)) {
            return false;
        }
        return id != null && id.equals(((Sallereservation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Sallereservation{" +
            "id=" + getId() +
            ", datereservation='" + getDatereservation() + "'" +
            "}";
    }
}
