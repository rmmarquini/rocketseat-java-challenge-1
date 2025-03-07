package dev.rmmarquini.repository;

import dev.rmmarquini.entity.Author;
import dev.rmmarquini.entity.Book;
import dev.rmmarquini.entity.Library;
import dev.rmmarquini.enums.BooksManagementOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BooksRepository extends AbstractRepository {

	private final Logger logger = LogManager.getLogger(BooksRepository.class.getName());

	private final Library.Builder libraryBuilder;
	private final Library library;

	public BooksRepository(Library.Builder libraryBuilder, Library library) {
		this.libraryBuilder = libraryBuilder;
		this.library = library;
	}

	@Override
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

	@Override
	public void manage(Scanner scanner) {

		boolean keepManaging = true;
		List<BooksManagementOptions> nav = BooksManagementOptions.getValues();

		while (keepManaging) {

			logger.info("Please, choose an option to manage the books:");
			for (BooksManagementOptions value : nav) {
				logger.info("{} - {}", value.getOption(), value.getDescription());
			}
			int booksOption = scanner.nextInt();

			BooksManagementOptions selectedBooksOption = BooksManagementOptions.getEnumByOption(booksOption);

			while (selectedBooksOption == null) {
				logger.error("Invalid books option. Please, try again...");
				booksOption = scanner.nextInt();
				selectedBooksOption = BooksManagementOptions.getEnumByOption(booksOption);
			}

			logger.info("Books management option selected by user: {}", selectedBooksOption.getDescription());

			switch (selectedBooksOption) {
				case ADD_BOOK:

					List<Author> bookAuthors = new LinkedList<>();
					boolean keepAddingAuthors = true;

					scanner.nextLine(); // Consume the newline character

					logger.info("Please, inform the book's author(s):");
					while (keepAddingAuthors) {
						logger.info("Type the author's name:");
						String authorName = scanner.nextLine();

						logger.info("Type the author's birth date (yyyy-mm-dd):");
						String authorBirthDate = scanner.nextLine();
						String[] authorBirthDateParts = authorBirthDate.split("-");

						Author author = new Author(
								generateId(),
								authorName,
								LocalDate.of(Integer.parseInt(authorBirthDateParts[0]), Integer.parseInt(authorBirthDateParts[1]), Integer.parseInt(authorBirthDateParts[2]))
						);

						library.getAuthors().stream()
								.filter(a -> a.getName().equalsIgnoreCase(authorName))
								.findFirst()
								.ifPresentOrElse(
										bookAuthors::add,
										() -> {
											libraryBuilder.addAuthor(author);
											libraryBuilder.build();
											bookAuthors.add(author);
										}
								);

						logger.info("Do you want to add another author? (Y/N)");
						String answer = scanner.nextLine();
						if (answer.equalsIgnoreCase("N")) {
							keepAddingAuthors = false;
						}
					}

					logger.info("Please, inform the book's title:");
					String bookTitle = scanner.nextLine();

					Book book = new Book(generateId(), bookTitle, true, LocalDate.now());
					book.setAuthors(bookAuthors);

					library.getBooks().stream()
							.filter(b -> b.getTitle().equalsIgnoreCase(bookTitle))
							.findFirst()
							.ifPresentOrElse(
									b -> logger.info("Book already exists."),
									() -> {
										libraryBuilder.addBook(book);
										libraryBuilder.build();
										logger.info("Book added successfully.");
									}
							);

					break;

				case UPDATE_BOOK:
					// TODO: implement update book
					logger.info("Not available yet.");
					break;

				case DELETE_BOOK:
					// TODO: implement delete book
					logger.info("Not available yet.");
					break;

				case LIST_BOOKS:
					logger.info("Books: {}", library.getBooks());
					break;

				case SEARCH_BOOK_BY_TITLE:
					logger.info("Please, enter the book title:");
					scanner.nextLine(); // Consume the newline character
					String title = scanner.nextLine();
					Book bookByTitle = library.getBookByTitle(title);
					logger.info(message(bookByTitle));
					break;

				case SEARCH_BOOK_BY_AUTHOR:
					logger.info("Please, enter the author name:");
					scanner.nextLine(); // Consume the newline character
					String authorName = scanner.nextLine();
					List<Book> booksByAuthor = library.getBookByAuthor(authorName);
					if (booksByAuthor == null) {
						logger.info("Author not found.");
						break;
					}
					for (Book b : booksByAuthor) logger.info(message(b));
					break;

				default:
					keepManaging = false;
					break;  // EXIT
			}

		}

	}

	private String message(Book book) {
		return book != null ? "Book found: " + book : "Book not found.";
	}

}
