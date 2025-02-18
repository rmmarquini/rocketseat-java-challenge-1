package dev.rmmarquini;

import dev.rmmarquini.enums.LibraryManagementOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class.getName());

	public static void main(String[] args) {

		logger.info(new String(new char[25]).replace("\0", "-"));
		logger.info("Welcome to the Library Management System!");

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

			if (selectedOption == null) {
				logger.error("Invalid option. Please, try again.");
				return;
			}

			switch (selectedOption) {
				case BOOKS:
					logger.info("You selected: {}", selectedOption.getDescription());
					break;
				case AUTHORS:
					logger.info("You selected: {}", selectedOption.getDescription());
					break;
				case USERS:
					logger.info("You selected: {}", selectedOption.getDescription());
					break;
				case LOANS:
					logger.info("You selected: {}", selectedOption.getDescription());
					break;
				case EXIT:
					logger.info("You selected: {}", selectedOption.getDescription());
					keepRunning = false;
					break;
				default:
					logger.error("Invalid option. Please, try again.");
					keepRunning = false;
					break;
			}

		}


		logger.info("Goodbye!");
		logger.info(new String(new char[25]).replace("\0", "-"));

	}

}
