package com.datalake.reservationsalle.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Equipements.class)
public abstract class Equipements_ {

	public static volatile SingularAttribute<Equipements, Salle> salle;
	public static volatile SingularAttribute<Equipements, String> name;
	public static volatile SingularAttribute<Equipements, Long> id;

	public static final String SALLE = "salle";
	public static final String NAME = "name";
	public static final String ID = "id";

}

