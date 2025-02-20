package dev.rmmarquini.enums;

import java.util.List;

public enum BooksManagementOptions {

	ADD_BOOK(1, "Add new book"),
	UPDATE_BOOK(2, "Update book"),
	LOAN_BOOK(3, "Loan book"),
	LIST_BOOKS(4, "List books"),
	SEARCH_BOOK_BY_TITLE(5, "Search book by title"),
	SEARCH_BOOK_BY_AUTHOR(6, "Search books by author"),
	EXIT(7, "Back to main menu");

	public final int option;
	public final String description;

	BooksManagementOptions(int option, String description) {
		this.option = option;
		this.description = description;
	}

	public int getOption() {
		return option;
	}

	public String getDescription() {
		return description;
	}

	public static BooksManagementOptions getEnumByOption(int option) {
		for (BooksManagementOptions value : BooksManagementOptions.values()) {
			if (value.getOption() == option) {
				return value;
			}
		}
		return null;
	}

	public static BooksManagementOptions getEnumByDescription(String description) {
		for (BooksManagementOptions value : BooksManagementOptions.values()) {
			if (value.getDescription().equals(description)) {
				return value;
			}
		}
		return null;
	}

	public static BooksManagementOptions getEnum(int option, String description) {
		for (BooksManagementOptions value : BooksManagementOptions.values()) {
			if (value.getOption() == option && value.getDescription().equals(description)) {
				return value;
			}
		}
		return null;
	}

	public static List<BooksManagementOptions> getValues() {
		return List.of(BooksManagementOptions.values());
	}

}
