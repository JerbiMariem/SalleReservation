package com.datalake.reservationsalle.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Salle.
 */
@Entity
@Table(name = "salle")
public class Salle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL)
    private Set<Equipements> equipements = new HashSet<>();

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL)
    private Set<Sallereservation> reservationsalles = new HashSet<>();

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

    public Salle name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Salle capacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Set<Equipements> getEquipements() {
        return equipements;
    }

    public Salle equipements(Set<Equipements> equipements) {
        this.equipements = equipements;
        return this;
    }

    public Salle addEquipements(Equipements equipements) {
        this.equipements.add(equipements);
        equipements.setSalle(this);
        return this;
    }

    public Salle removeEquipements(Equipements equipements) {
        this.equipements.remove(equipements);
        equipements.setSalle(null);
        return this;
    }

    public void setEquipements(Set<Equipements> equipements) {
        this.equipements = equipements;
    }

    public Set<Sallereservation> getReservationsalles() {
        return reservationsalles;
    }

    public Salle reservationsalles(Set<Sallereservation> reservationsalles) {
        this.reservationsalles = reservationsalles;
        return this;
    }

    public Salle addReservationsalle(Sallereservation reservationsalle) {
        this.reservationsalles.add(reservationsalle);
        reservationsalle.setSalle(this);
        return this;
    }

    public Salle removeReservationsalle(Sallereservation reservationsalle) {
        this.reservationsalles.remove(reservationsalle);
        reservationsalle.setSalle(null);
        return this;
    }

    public void setReservationsalles(Set<Sallereservation> reservationsalles) {
        this.reservationsalles = reservationsalles;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Salle)) {
            return false;
        }
        return id != null && id.equals(((Salle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Salle{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", capacity=" + getCapacity() +
            "}";
    }
}
