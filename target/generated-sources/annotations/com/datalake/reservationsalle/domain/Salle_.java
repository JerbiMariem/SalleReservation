package com.datalake.reservationsalle.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Salle.class)
public abstract class Salle_ {

	public static volatile SingularAttribute<Salle, String> name;
	public static volatile SetAttribute<Salle, Equipements> equipements;
	public static volatile SingularAttribute<Salle, Long> id;
	public static volatile SetAttribute<Salle, Sallereservation> reservationsalles;
	public static volatile SingularAttribute<Salle, Integer> capacity;

	public static final String NAME = "name";
	public static final String EQUIPEMENTS = "equipements";
	public static final String ID = "id";
	public static final String RESERVATIONSALLES = "reservationsalles";
	public static final String CAPACITY = "capacity";

}

