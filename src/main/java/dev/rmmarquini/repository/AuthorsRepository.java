package dev.rmmarquini.repository;

import dev.rmmarquini.entity.Author;
import dev.rmmarquini.entity.Library;
import dev.rmmarquini.enums.AuthorsManagementOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AuthorsRepository extends AbstractRepository {

	private final Logger logger = LogManager.getLogger(AuthorsRepository.class.getName());

	private final Library.Builder libraryBuilder;
	private final Library library;

	public AuthorsRepository(Library.Builder libraryBuilder, Library library) {
		this.libraryBuilder = libraryBuilder;
		this.library = library;
	}

	@Override
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

	@Override
	public void manage(Scanner scanner) {

		boolean keepManaging = true;
		List<AuthorsManagementOptions> nav = AuthorsManagementOptions.getValues();

		while (keepManaging) {
			logger.info("Please, choose an option to manage authors:");
			for (AuthorsManagementOptions value : nav) {
				logger.info("{} - {}", value.getOption(), value.getDescription());
			}
			int authorsOption = scanner.nextInt();

			AuthorsManagementOptions selectedAuthorsOption = AuthorsManagementOptions.getEnumByOption(authorsOption);

			while (selectedAuthorsOption == null) {
				logger.error("Invalid authors option. Please, try again...");
				authorsOption = scanner.nextInt();
				selectedAuthorsOption = AuthorsManagementOptions.getEnumByOption(authorsOption);
			}
			scanner.nextLine();

			logger.info("Authors management option selected by user: {}", selectedAuthorsOption.getDescription());

			switch (selectedAuthorsOption) {
				case ADD_AUTHOR:
					logger.info("Please, inform the author's name:");
					String authorName = scanner.nextLine();

					Author authorToAdd = library.getAuthorByName(authorName);

					if (authorToAdd != null) {
						logger.error("Author already exists.");
					} else {
						logger.info("Please, inform the author's birth date (yyyy-MM-dd):");
						String authorBirthDate = scanner.nextLine();
						LocalDate birthDate = LocalDate.parse(authorBirthDate);
						Author author = new Author(generateId(), authorName, birthDate);
						libraryBuilder.addAuthor(author);
						libraryBuilder.build();
						logger.info("Author added successfully.");
					}
					break;

				case UPDATE_AUTHOR:
					logger.info("Please, inform the author's name:");
					String authorNameToUpdate = scanner.nextLine();

					Author authorToUpdate = library.getAuthorByName(authorNameToUpdate);

					if (authorToUpdate == null) {
						logger.error("Author not found. Please, try again...");
					} else {
						logger.info("Please, inform the author's new name:");
						String newAuthorName = scanner.nextLine();

						logger.info("Please, inform the author's new birth date (yyyy-MM-dd):");
						String newAuthorBirthDate = scanner.nextLine();
						LocalDate newBirthDate = LocalDate.parse(newAuthorBirthDate);

						authorToUpdate.setName(newAuthorName);
						authorToUpdate.setBirthDate(newBirthDate);

						libraryBuilder.updateAuthor(authorToUpdate);
						libraryBuilder.build();
						logger.info("Author updated successfully.");
					}
					break;

				case REMOVE_AUTHOR:
					logger.info("Please, inform the author's name:");
					String authorNameToDelete = scanner.nextLine();

					Author authorToDelete = library.getAuthorByName(authorNameToDelete);

					if (authorToDelete == null) {
						logger.error("Author not found.");
					} else {
						libraryBuilder.removeAuthor(authorToDelete.getId());
						libraryBuilder.build();
						logger.info("Author removed successfully.");
					}
					break;

				case LIST_AUTHORS:
					logger.info("Authors: {}", library.getAuthors());
					break;

				case SEARCH_AUTHOR_BY_NAME:
					logger.info("Please, inform the author's name:");
					String authorNameToSearch = scanner.nextLine();

					Author authorToSearch = library.getAuthorByName(authorNameToSearch);

					if (authorToSearch == null) {
						logger.error("Author not found.");
					} else {
						logger.info("Author found: {}", authorToSearch);
					}
					break;

				default:
					keepManaging = false;
					break; // EXIT
			}

		}

	}

}
