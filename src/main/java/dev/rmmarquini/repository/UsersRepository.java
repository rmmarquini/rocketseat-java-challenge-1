package dev.rmmarquini.repository;

import dev.rmmarquini.entity.Library;
import dev.rmmarquini.entity.User;
import dev.rmmarquini.enums.UsersManagementOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UsersRepository extends AbstractRepository {

	private final Logger logger = LogManager.getLogger(UsersRepository.class.getName());

	private final Library.Builder libraryBuilder;
	private final Library library;

	public UsersRepository(Library.Builder libraryBuilder, Library library) {
		this.libraryBuilder = libraryBuilder;
		this.library = library;
	}

	@Override
	public void load() {

		User user1 = new User(generateId(), "John Doe", "john.doe@email.com", LocalDate.of(1990, 1, 1));
		User user2 = new User(generateId(), "Jane Doe", "jane.doe@email.com", LocalDate.of(1993, 2, 2));
		User user3 = new User(generateId(), "Alice Doe", "alice.doe@email.com", LocalDate.of(1995, 3, 3));

		libraryBuilder
				.addUser(user1)
				.addUser(user2)
				.addUser(user3);

	}

	@Override
	public void manage(Scanner scanner) {
		// TODO: enumerate options for manage users
		// TODO: add user
		// TODO: update user
		// TODO: remove user
		// TODO: list all users
		// TODO: search user by name

		boolean keepManaging = true;
		List<UsersManagementOptions> nav = UsersManagementOptions.getValues();

		while (keepManaging) {

			logger.info("Please, choose an option to manage users:");
			for (UsersManagementOptions value : nav) {
				logger.info("{} - {}", value.getOption(), value.getDescription());
			}
			int usersOption = scanner.nextInt();

			UsersManagementOptions selectedOption = UsersManagementOptions.getEnumByOption(usersOption);

			while (selectedOption == null) {
				logger.error("Invalid option. Please, choose a valid option:");
				usersOption = scanner.nextInt();
				selectedOption = UsersManagementOptions.getEnumByOption(usersOption);
			}
			scanner.nextLine(); // Consume the newline character

			logger.info("User selected: {}", selectedOption.getDescription());

			switch (selectedOption) {
				case ADD_USER:
					logger.info("Please, inform the user's name:");
					String userName = scanner.nextLine();

					logger.info("Please, inform the user's email:");
					String userEmail = scanner.nextLine();

					logger.info("Please, inform the user's birth date (yyyy-MM-dd):");
					String userBirthDate = scanner.nextLine();

					User user = new User(generateId(), userName, userEmail, LocalDate.parse(userBirthDate));

					libraryBuilder.addUser(user).build();
					break;

				case UPDATE_USER:
					logger.info("Please, inform the user's name would you like to update:");
					String userNameToUpdate = scanner.nextLine();

					User userToUpdate = library.getUserByName(userNameToUpdate);

					if (userToUpdate != null) {
						logger.info("Please, inform the user's new name:");
						String newUserName = scanner.nextLine();

						logger.info("Please, inform the user's new email:");
						String newUserEmail = scanner.nextLine();

						logger.info("Please, inform the user's new birth date (yyyy-MM-dd):");
						String newUserBirthDate = scanner.nextLine();
						LocalDate newBirthDate = LocalDate.parse(newUserBirthDate);

						userToUpdate.setName(newUserName);
						userToUpdate.setEmail(newUserEmail);
						userToUpdate.setBirthDate(newBirthDate);

						libraryBuilder.updateUser(userToUpdate).build();
						logger.info("User updated successfully.");
					} else {
						logger.error("User not found.");
					}

					break;

				case DELETE_USER:
					logger.info("Please, inform the user's name would you like to delete:");
					String userNameToDelete = scanner.nextLine();
					User userToDelete = library.getUserByName(userNameToDelete);
					libraryBuilder.removeUser(userToDelete.getId()).build();
					break;

				case LIST_USERS:
					logger.info("Users: {}", library.getUsers());
					break;

				case SEARCH_USER_BY_NAME:
					logger.info("Please, inform a name:");
					String name = scanner.nextLine();
					User userFound = library.getUserByName(name);
					if (userFound != null) {
						logger.info("User found: {}", userFound);
					} else {
						logger.info("User not found.");
					}
					break;

				default:
					keepManaging = false;
					break;
			}

		}

	}

}
