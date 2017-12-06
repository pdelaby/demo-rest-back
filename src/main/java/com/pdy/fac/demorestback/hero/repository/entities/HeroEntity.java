package com.pdy.fac.demorestback.hero.repository.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.pdy.fac.demorestback.commons.Mappable;

@Entity
@Table(name = "hero")
public class HeroEntity implements Mappable {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id")
	private String id;

	@Column(name = "nom")
	private String nom;

	public HeroEntity() {
		super();
	}

	public HeroEntity(final String id, final String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

}
