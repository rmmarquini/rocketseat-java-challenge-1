package dev.rmmarquini.repository;

import dev.rmmarquini.entity.Author;
import dev.rmmarquini.entity.Library;

import java.time.LocalDate;
import java.util.Scanner;

public class AuthorsRepository extends AbstractRepository {

	private final Library.Builder libraryBuilder;
	private final Library library;

	public AuthorsRepository(Library.Builder libraryBuilder, Library library) {
		this.libraryBuilder = libraryBuilder;
		this.library = library;
	}

	public void load() {

		Author author1 = new Author(generateId(), "Robert C. Martin", LocalDate.of(1952, 12, 5));
		Author author2 = new Author(generateId(), "Martin Fowler", LocalDate.of(1963, 12, 18));
		Author author3 = new Author(generateId(), "Neal Ford", LocalDate.of(1971, 5, 1));
		Author author4 = new Author(generateId(), "Mark Richards", LocalDate.of(1962, 8, 1));
		Author author5 = new Author(generateId(), "J. R. R. Tolkien", LocalDate.of(1892, 1, 3));
		Author author6 = new Author(generateId(), "J. K. Rowling", LocalDate.of(1965, 7, 31));
		Author author7 = new Author(generateId(), "George R. R. Martin", LocalDate.of(1948, 9, 20));
		Author author8 = new Author(generateId(), "Susan J. Fowler", LocalDate.of(1983, 8, 10));

		libraryBuilder.addAuthor(author1)
			.addAuthor(author2)
			.addAuthor(author3)
			.addAuthor(author4)
			.addAuthor(author5)
			.addAuthor(author6)
			.addAuthor(author7)
			.addAuthor(author8);

	}

	public void manage(Scanner scanner) {

	}

}
