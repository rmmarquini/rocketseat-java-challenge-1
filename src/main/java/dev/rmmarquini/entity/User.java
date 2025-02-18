package dev.rmmarquini.entity;

import java.time.LocalDate;
import java.util.Objects;

public class User {

	private final String id;
	private String name;
	private String email;
	private LocalDate birthDate;

	public User(String id, String name, String email, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(birthDate, user.birthDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, email, birthDate);
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", birthDate=" + birthDate +
				'}';
	}

}
