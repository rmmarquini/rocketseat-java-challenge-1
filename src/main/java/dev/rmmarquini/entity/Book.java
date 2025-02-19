package dev.rmmarquini.entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Book {

	private final String id;
	private String title;
	private final List<Author> authors;
	private boolean available;
	private LocalDate registerDate;
	private LocalDate updateDate;

	public Book(String id, String title, boolean available, LocalDate registerDate) {
		this.id = id;
		this.title = title;
		this.authors = new LinkedList<>();
		this.available = available;
		this.registerDate = registerDate;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors.addAll(authors);
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return available == book.available && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(authors, book.authors) && Objects.equals(registerDate, book.registerDate) && Objects.equals(updateDate, book.updateDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, authors, available, registerDate, updateDate);
	}

	@Override
	public String toString() {
		return "Book{" +
				"id='" + id + '\'' +
				", title='" + title + '\'' +
				", author=" + authors +
				", available=" + available +
				", registerDate=" + registerDate +
				", updateDate=" + updateDate +
				'}';
	}

}
