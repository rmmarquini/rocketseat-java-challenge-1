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
		logger.info("Loading data...");
		Library.Builder libraryBuilder = new Library.Builder();

		AuthorsRepository authorsRepository = new AuthorsRepository(libraryBuilder);
		authorsRepository.load();

		Library library = libraryBuilder.build();

		BooksRepository booksRepository = new BooksRepository(libraryBuilder, library);
		booksRepository.load();

		UsersRepository usersRepository = new UsersRepository(libraryBuilder);
		usersRepository.load();

		library = libraryBuilder.build();

		if (library.getAuthors().isEmpty() && library.getBooks().isEmpty()) {
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
			logger.info("Please, choose an option:");
			for (LibraryManagementOptions value : nav) {
				logger.info("{} - {}", value.getOption(), value.getDescription());
			}

			int option = scanner.nextInt();
			LibraryManagementOptions selectedOption = LibraryManagementOptions.getEnumByOption(option);

			while (selectedOption == null) {
				logger.error("Invalid option. Please, try again...");
				option = scanner.nextInt();
				selectedOption = LibraryManagementOptions.getEnumByOption(option);
			}

			logger.info("User selected: {}", selectedOption.getDescription());

			switch (selectedOption) {
				case BOOKS:
					break;

				case AUTHORS:
					break;

				case USERS:
					break;

				case LOANS:
					break;

				default: // EXIT
					keepRunning = false;
					break;
			}

		}

		logger.info("Goodbye!");
		logger.info(new String(new char[25]).replace("\0", "-"));

	}

}
