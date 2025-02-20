package dev.rmmarquini.repository;

import dev.rmmarquini.entity.Library;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
		// TODO: enumerate options for manage loans
		// TODO: list all loans
		// TODO: list loans by user
		// TODO: list loans by book
		// TODO: list loans by date
	}

}
