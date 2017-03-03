package edu.adias.powermock.easymock.demo;

import java.util.Date;

public final class Person {
	
	private String name;
	private java.util.Date birthDate;
	
	public Person(String name, Date birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public java.util.Date getBirthDate() {
		return birthDate;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", birthDate=" + birthDate + "]";
	}

}
