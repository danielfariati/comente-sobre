package com.danielfariati.comente_sobre.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subject implements Serializable {

	private static final long serialVersionUID = -2596990337070080513L;

	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
