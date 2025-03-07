package dev.rmmarquini.enums;

import java.util.List;

public enum BooksManagementOptions implements MenuOptions<BooksManagementOptions> {

	ADD_BOOK(1, "Add new book"),
	UPDATE_BOOK(2, "Update book"),
	DELETE_BOOK(3, "Delete book"),
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

	@Override
	public int getOption() {
		return this.option;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	public static BooksManagementOptions getEnumByOption(int option) {
		return MenuOptions.getEnumByOption(BooksManagementOptions.class, option);
	}

	public static BooksManagementOptions getEnumByDescription(String description) {
		return MenuOptions.getEnumByDescription(BooksManagementOptions.class, description);
	}

	public static BooksManagementOptions getEnum(int option, String description) {
		return MenuOptions.getEnum(BooksManagementOptions.class, option, description);
	}

	public static List<BooksManagementOptions> getValues() {
		return MenuOptions.getValues(BooksManagementOptions.class);
	}

}
