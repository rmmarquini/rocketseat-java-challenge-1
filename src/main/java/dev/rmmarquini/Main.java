package dev.rmmarquini;

import dev.rmmarquini.entity.Library;
import dev.rmmarquini.enums.LibraryManagementOptions;
import dev.rmmarquini.repository.AuthorsRepository;
import dev.rmmarquini.repository.BooksRepository;
import dev.rmmarquini.repository.UsersRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class.getName());

	public static void main(String[] args) {

		logger.info(new String(new char[25]).replace("\0", "-"));
		logger.info("Welcome to the Library Management System!");

		// -----------------------------------------------------------------------------------------------------------
		logger.info("Loading library's data...");
		Library.Builder libraryBuilder = new Library.Builder();
		Library library = libraryBuilder.build();

		AuthorsRepository authorsRepository = new AuthorsRepository(libraryBuilder, library);
		authorsRepository.load();
		library = libraryBuilder.build();

		BooksRepository booksRepository = new BooksRepository(libraryBuilder, library);
		booksRepository.load();
		library = libraryBuilder.build();

		UsersRepository usersRepository = new UsersRepository(libraryBuilder, library);
		usersRepository.load();
		library = libraryBuilder.build();

		if (library.getAuthors().isEmpty() && library.getBooks().isEmpty() && library.getUsers().isEmpty()) {
			logger.error("No data loaded. Exiting...");
			System.exit(1);
		} else {
			logger.info("Data loaded successfully!");
			logger.info("Authors: {}", library.getAuthors());
			logger.info("Books: {}", library.getBooks());
			logger.info("Users: {}", library.getUsers());
		}

		// -----------------------------------------------------------------------------------------------------------
		Scanner scanner = new Scanner(System.in);
		boolean keepRunning = true;
		List<LibraryManagementOptions> nav = LibraryManagementOptions.getValues();

		while (keepRunning) {
			logger.info("Please, choose an option to manage the library:");
			for (LibraryManagementOptions value : nav) {
				logger.info("{} - {}", value.getOption(), value.getDescription());
			}

			int systemOption = scanner.nextInt();
			LibraryManagementOptions selectedSystemOption = LibraryManagementOptions.getEnumByOption(systemOption);

			while (selectedSystemOption == null) {
				logger.error("Invalid system option. Please, try again...");
				systemOption = scanner.nextInt();
				selectedSystemOption = LibraryManagementOptions.getEnumByOption(systemOption);
			}

			logger.info("User selected: {}", selectedSystemOption.getDescription());

			switch (selectedSystemOption) {
				case BOOKS:
					booksRepository.manage(scanner);
					break;

				case AUTHORS:
					// TODO: enumerate options for manage authors
					// TODO: add author
					// TODO: update author
					// TODO: remove author
					// TODO: list all authors
					// TODO: search author by name
					break;

				case USERS:
					// TODO: enumerate options for manage users
					// TODO: add user
					// TODO: update user
					// TODO: remove user
					// TODO: list all users
					// TODO: search user by name
					break;

				case LOANS:
					// TODO: enumerate options for manage loans
					// TODO: list all loans
					// TODO: list loans by user
					// TODO: list loans by book
					// TODO: list loans by date
					break;

				default: // EXIT
					keepRunning = false;
					break;
			}

		}

		scanner.close();

		logger.info("Goodbye!");
		logger.info(new String(new char[25]).replace("\0", "-"));

	}

}
