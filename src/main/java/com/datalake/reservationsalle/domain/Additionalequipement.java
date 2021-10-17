package com.datalake.reservationsalle.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Additionalequipement.
 */
@Entity
@Table(name = "additionalequipement")
public class Additionalequipement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private Integer number;

    @OneToMany(mappedBy = "additionalequipement")
    private Set<Equipementsreservation> equipementsreservations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Additionalequipement name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public Additionalequipement number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Set<Equipementsreservation> getEquipementsreservations() {
        return equipementsreservations;
    }

    public Additionalequipement equipementsreservations(Set<Equipementsreservation> equipementsreservations) {
        this.equipementsreservations = equipementsreservations;
        return this;
    }

    public Additionalequipement addEquipementsreservation(Equipementsreservation equipementsreservation) {
        this.equipementsreservations.add(equipementsreservation);
        equipementsreservation.setAdditionalequipement(this);
        return this;
    }

    public Additionalequipement removeEquipementsreservation(Equipementsreservation equipementsreservation) {
        this.equipementsreservations.remove(equipementsreservation);
        equipementsreservation.setAdditionalequipement(null);
        return this;
    }

    public void setEquipementsreservations(Set<Equipementsreservation> equipementsreservations) {
        this.equipementsreservations = equipementsreservations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Additionalequipement)) {
            return false;
        }
        return id != null && id.equals(((Additionalequipement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Additionalequipement{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", number=" + getNumber() +
            "}";
    }
}
