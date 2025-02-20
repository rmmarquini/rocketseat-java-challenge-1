package dev.rmmarquini.repository;

import dev.rmmarquini.entity.Library;
import dev.rmmarquini.entity.User;

import java.time.LocalDate;
import java.util.Scanner;

public class UsersRepository extends AbstractRepository {

	private final Library.Builder libraryBuilder;
	private final Library library;

	public UsersRepository(Library.Builder libraryBuilder, Library library) {
		this.libraryBuilder = libraryBuilder;
		this.library = library;
	}

	public void load() {

		User user1 = new User(generateId(), "John Doe", "john.doe@email.com", LocalDate.of(1990, 1, 1));
		User user2 = new User(generateId(), "Jane Doe", "jane.doe@email.com", LocalDate.of(1993, 2, 2));
		User user3 = new User(generateId(), "Alice Doe", "alice.doe@email.com", LocalDate.of(1995, 3, 3));

		libraryBuilder
				.addUser(user1)
				.addUser(user2)
				.addUser(user3);

	}

	public void manage(Scanner scanner) {

	}

}
