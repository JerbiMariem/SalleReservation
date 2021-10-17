package com.datalake.reservationsalle.domain;

import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sallereservation.class)
public abstract class Sallereservation_ {

	public static volatile SingularAttribute<Sallereservation, Salle> salle;
	public static volatile SingularAttribute<Sallereservation, LocalTime> datereservation;
	public static volatile SingularAttribute<Sallereservation, Long> id;

	public static final String SALLE = "salle";
	public static final String DATERESERVATION = "datereservation";
	public static final String ID = "id";

}

