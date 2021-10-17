package com.datalake.reservationsalle.domain;

import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Equipementsreservation.class)
public abstract class Equipementsreservation_ {

	public static volatile SingularAttribute<Equipementsreservation, Additionalequipement> additionalequipement;
	public static volatile SingularAttribute<Equipementsreservation, LocalTime> datereservation;
	public static volatile SingularAttribute<Equipementsreservation, Long> id;

	public static final String ADDITIONALEQUIPEMENT = "additionalequipement";
	public static final String DATERESERVATION = "datereservation";
	public static final String ID = "id";

}

