package dev.rmmarquini.repository;

import dev.rmmarquini.entity.Book;
import dev.rmmarquini.entity.Library;
import dev.rmmarquini.entity.Loan;
import dev.rmmarquini.entity.User;
import dev.rmmarquini.enums.LoansManagementOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class LoansRepository extends AbstractRepository {

	private final Logger logger = LogManager.getLogger(LoansRepository.class.getName());

	private final Library.Builder libraryBuilder;
	private final Library library;

	public LoansRepository(Library.Builder libraryBuilder, Library library) {
		this.libraryBuilder = libraryBuilder;
		this.library = library;
	}

	@Override
	public void load() {
		// not implemented
	}

	@Override
	public void manage(Scanner scanner) {

		boolean keepManaging = true;
		List<LoansManagementOptions> nav = LoansManagementOptions.getValues();

		while (keepManaging) {

			logger.info("Please, choose an option to manage loans:");
			for (LoansManagementOptions value : nav) {
				logger.info("{} - {}", value.getOption(), value.getDescription());
			}
			int loansOption = scanner.nextInt();

			LoansManagementOptions selectedOption = LoansManagementOptions.getEnumByOption(loansOption);

			while (selectedOption == null) {
				logger.error("Invalid option. Please, choose a valid option:");
				loansOption = scanner.nextInt();
				selectedOption = LoansManagementOptions.getEnumByOption(loansOption);
			}
			scanner.nextLine(); // Consume the newline character

			switch (selectedOption) {
				case LOAN_BOOK:
					logger.info("Please, inform the user name would you like to loan the book:");
					String userNameToLoan = scanner.nextLine();
					User userToLoan = library.getUserByName(userNameToLoan);

					List<Book> booksToLoan = new ArrayList<>();
					boolean keepAddingBooks = true;

					while (keepAddingBooks) {
						logger.info("Please, inform the book title would you like to loan:");
						String bookTitleToLoan = scanner.nextLine();
						Book bookToLoan = library.getBookByTitle(bookTitleToLoan);

						if (bookToLoan == null) {
							logger.error("Book not found. Please, try again...");
							break;
						}

						bookToLoan.setAvailable(false);
						booksToLoan.add(bookToLoan);

						logger.info("Would you like to loan another book? (Y/N)");
						String answer = scanner.nextLine();
						if (!answer.equalsIgnoreCase("Y")) {
							keepAddingBooks = false;
						}
					}

					if (userToLoan == null || booksToLoan.isEmpty()) {
						logger.error("User or book not found. Please, try again...");
						break;
					}

					Loan loan = new Loan(generateId(), userToLoan, booksToLoan);
					loan.setCheckOutDate(LocalDate.now());

					libraryBuilder.addLoan(loan).build();
					logger.info("Loan added successfully.");
					break;

				case RETURN_BOOK:
					logger.info("Please, inform the user name would you like to return the book:");
					String userNameToReturn = scanner.nextLine();
					User userToReturn = library.getUserByName(userNameToReturn);

					List<Book> booksToReturn = new ArrayList<>();
					boolean keepReturningBooks = true;

					while (keepReturningBooks) {
						logger.info("Please, inform the book title would you like to return:");
						String bookTitleToReturn = scanner.nextLine();
						Book bookToReturn = library.getBookByTitle(bookTitleToReturn);

						if (bookToReturn == null) {
							logger.error("Book not found.");
							break;
						}

						bookToReturn.setAvailable(true);
						booksToReturn.add(bookToReturn);

						logger.info("Would you like to return another book? (Y/N)");
						String answer = scanner.nextLine();
						if (!answer.equalsIgnoreCase("Y")) {
							keepReturningBooks = false;
						}
					}

					Loan loanToReturn = library.getLoansByUser(userToReturn).stream()
							.filter(l -> new HashSet<>(l.getCheckedOutBooks()).containsAll(booksToReturn))
							.findFirst()
							.orElse(null);

					if (loanToReturn == null) {
						logger.error("Loan not found.");
						break;
					}

					loanToReturn.getCheckedOutBooks().forEach(book -> book.setAvailable(true));

					libraryBuilder.updateLoan(loanToReturn).build();

					break;

				case LIST_LOANS:
					logger.info("Loans: {}", library.getLoans());
					break;

				case LIST_LOANS_BY_USER:
					logger.info("Please, inform the user name would you like to list the loans:");
					String userName = scanner.nextLine();
					User userToCheckLoans = library.getUserByName(userName);
					logger.info("User's loans: {}", library.getLoansByUser(userToCheckLoans));
					break;

				case LIST_LOANS_BY_BOOK:
					logger.info("Please, inform the book title would you like to list the loans:");
					String bookTitle = scanner.nextLine();
					Book bookToCheckLoans = library.getBookByTitle(bookTitle);
					logger.info("Book's loans: {}", library.getLoansByBook(bookToCheckLoans));
					break;

				case LIST_LOANS_BY_DATE:
					logger.info("Please, inform the date (yyyy-MM-dd) would you like to list the loans:");
					String date = scanner.nextLine();
					logger.info("Loans by date: {}", library.getLoansByDate(date));
					break;

				default:
					keepManaging = false;
					break;
			}

		}

	}

}
