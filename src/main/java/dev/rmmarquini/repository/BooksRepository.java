package dev.rmmarquini.repository;

import dev.rmmarquini.entity.Author;
import dev.rmmarquini.entity.Book;
import dev.rmmarquini.entity.Library;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class BooksRepository extends AbstractRepository {

	private final Library.Builder libraryBuilder;
	private final Library library;

	public BooksRepository(Library.Builder libraryBuilder, Library library) {
		this.libraryBuilder = libraryBuilder;
		this.library = library;
	}

	public void load() {

		List<Author> tmpAuthors = new LinkedList<>();

		Book book1 = new Book(generateId(), "Clean Code", true, LocalDate.of(2008, 8, 11));
		tmpAuthors.add(library.getAuthorByName("Robert C. Martin"));
		book1.setAuthors(tmpAuthors);

		tmpAuthors.clear();

		Book book2 = new Book(generateId(), "Domain-Driven Design", true, LocalDate.of(2003, 8, 30));
		tmpAuthors.add(library.getAuthorByName("Martin Fowler"));
		book2.setAuthors(tmpAuthors);

		tmpAuthors.clear();

		Book book3 = new Book(generateId(), "The Lord of the Rings", true, LocalDate.of(1954, 7, 29));
		tmpAuthors.add(library.getAuthorByName("J. R. R. Tolkien"));
		book3.setAuthors(tmpAuthors);

		tmpAuthors.clear();

		Book book4 = new Book(generateId(), "Harry Potter and the Philosopher's Stone", true, LocalDate.of(1997, 6, 26));
		tmpAuthors.add(library.getAuthorByName("J. K. Rowling"));
		book4.setAuthors(tmpAuthors);

		tmpAuthors.clear();

		Book book5 = new Book(generateId(), "Game of Thrones", true, LocalDate.of(1996, 8, 6));
		tmpAuthors.add(library.getAuthorByName("George R. R. Martin"));
		book5.setAuthors(tmpAuthors);

		tmpAuthors.clear();

		Book book6 = new Book(generateId(), "Refactoring", true, LocalDate.of(1999, 7, 8));
		tmpAuthors.add(library.getAuthorByName("Martin Fowler"));
		book6.setAuthors(tmpAuthors);

		tmpAuthors.clear();

		libraryBuilder
				.addBook(book1)
				.addBook(book2)
				.addBook(book3)
				.addBook(book4)
				.addBook(book5)
				.addBook(book6);

	}

}
