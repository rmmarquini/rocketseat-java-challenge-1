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
	private final AuthorsRepository authorsRepository;

	public BooksRepository(Library.Builder libraryBuilder, Library library, AuthorsRepository authorsRepository) {
		this.libraryBuilder = libraryBuilder;
		this.library = library;
		this.authorsRepository = authorsRepository;
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
			scanner.nextLine(); // Consume the newline character

			logger.info("Books management option selected by user: {}", selectedBooksOption.getDescription());

			switch (selectedBooksOption) {
				case ADD_BOOK:
					List<Author> bookAuthors = new LinkedList<>();

					logger.info("Please, inform the book's author(s):");
					associateAuthorsWithBook(scanner, bookAuthors);

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
										libraryBuilder.addBook(book).build();
										logger.info("Book added successfully.");
									}
							);
					break;

				case UPDATE_BOOK:
					logger.info("Inform which book you want to update:");
					String bookTitleToUpdate = scanner.nextLine();

					Book bookToUpdate = library.getBookByTitle(bookTitleToUpdate);

					if (bookToUpdate == null) {
						logger.error("Book not found. Please, try again...");
					} else {
						logger.info("Please, inform the book's new title:");
						String newBookTitle = scanner.nextLine();

						logger.info("Please, inform the book's new status (true/false):");
						boolean newBookStatus = scanner.nextBoolean();

						bookToUpdate.setTitle(newBookTitle);
						bookToUpdate.setAvailable(newBookStatus);

						List<Author> bookToUpdateAuthors = bookToUpdate.getAuthors();
						logger.info("Book's authors: {}", bookToUpdateAuthors);
						scanner.nextLine(); // Consume the newline character

						logger.info("Would you like to add new authors? (Y/N)");
						String answer = scanner.nextLine();
						if (answer.equalsIgnoreCase("Y")) {
							associateAuthorsWithBook(scanner, bookToUpdateAuthors);
							bookToUpdate.setAuthors(bookToUpdateAuthors);
						}

						logger.info("Would you like to remove authors? (Y/N)");
						answer = scanner.nextLine();
						if (answer.equalsIgnoreCase("Y")) {
							removeAuthorFromBook(scanner, bookToUpdateAuthors);
							bookToUpdate.setAuthors(bookToUpdateAuthors);
						}

						libraryBuilder.updateBook(bookToUpdate).build();
						logger.info("Book updated successfully.");
					}

					break;

				case DELETE_BOOK:
					logger.info("Which book do you want to delete?");
					String bookTitleToDelete = scanner.nextLine();
					Book bookToDelete = library.getBookByTitle(bookTitleToDelete);
					libraryBuilder.removeBook(bookToDelete.getId()).build();
					logger.info("Book deleted successfully.");
					break;

				case LIST_BOOKS:
					logger.info("Books: {}", library.getBooks());
					break;

				case SEARCH_BOOK_BY_TITLE:
					logger.info("Please, enter the book title:");
					String title = scanner.nextLine();
					Book bookByTitle = library.getBookByTitle(title);
					logger.info(message(bookByTitle));
					break;

				case SEARCH_BOOK_BY_AUTHOR:
					logger.info("Please, enter the author name:");
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

	private void removeAuthorFromBook(Scanner scanner, List<Author> bookToUpdateAuthors) {
		boolean keepRemovingAuthors = true;
		while (keepRemovingAuthors) {
			logger.info("Please, inform the author's name:");
			String authorName = scanner.nextLine();
			Author author = library.getAuthorByName(authorName);
			bookToUpdateAuthors.removeIf(a -> a.getName().equalsIgnoreCase(author.getName()));
			logger.info("Do you want to remove another author? (Y/N)");
			String answer2 = scanner.nextLine();
			if (answer2.equalsIgnoreCase("N")) {
				keepRemovingAuthors = false;
			}
		}
	}

	private void associateAuthorsWithBook(Scanner scanner, List<Author> bookToUpdateAuthors) {
		boolean keepAddingAuthors = true;
		while (keepAddingAuthors) {
			logger.info("Please, inform the author's name:");
			String authorName = scanner.nextLine();
			Author author = authorsRepository.addOrGetAuthor(scanner, authorName);

			library.getAuthors().stream()
					.filter(a -> a.getName().equalsIgnoreCase(author.getName()))
					.findFirst()
					.ifPresentOrElse(
							bookToUpdateAuthors::add,
							() -> {
								logger.error("Author not found.");
							}
					);

			logger.info("Do you want to add another author? (Y/N)");
			String answer2 = scanner.nextLine();
			if (answer2.equalsIgnoreCase("N")) {
				keepAddingAuthors = false;
			}
		}
	}

	private String message(Book book) {
		return book != null ? "Book found: " + book : "Book not found.";
	}

}
