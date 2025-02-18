package dev.rmmarquini.entity;

import java.time.LocalDate;

public class Author {

	private final String id;
	private String name;
	private LocalDate birthDate;

	public Author(String id, String name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Author{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", birthDate=" + birthDate +
				'}';
	}

}
