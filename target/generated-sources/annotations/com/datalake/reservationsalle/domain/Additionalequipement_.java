package com.datalake.reservationsalle.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Additionalequipement.class)
public abstract class Additionalequipement_ {

	public static volatile SingularAttribute<Additionalequipement, Integer> number;
	public static volatile SetAttribute<Additionalequipement, Equipementsreservation> equipementsreservations;
	public static volatile SingularAttribute<Additionalequipement, String> name;
	public static volatile SingularAttribute<Additionalequipement, Long> id;

	public static final String NUMBER = "number";
	public static final String EQUIPEMENTSRESERVATIONS = "equipementsreservations";
	public static final String NAME = "name";
	public static final String ID = "id";

}

