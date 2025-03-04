package dev.rmmarquini.enums;

import java.util.List;

public enum LibraryManagementOptions implements MenuOptions<LibraryManagementOptions> {

	BOOKS(1, "Manage Books"),
	AUTHORS(2, "Manage Authors"),
	USERS(3, "Manage Users"),
	LOANS(4, "Manage Loans"),
	EXIT(5, "Exit");

	private final int option;
	private final String description;

	LibraryManagementOptions(int option, String description) {
		this.option = option;
		this.description = description;
	}

	@Override
	public int getOption() {
		return option;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public static LibraryManagementOptions getEnumByOption(int option) {
		return MenuOptions.getEnumByOption(LibraryManagementOptions.class, option);
	}

	public static LibraryManagementOptions getEnumByDescription(String description) {
		return MenuOptions.getEnumByDescription(LibraryManagementOptions.class, description);
	}

	public static LibraryManagementOptions getEnum(int option, String description) {
		return MenuOptions.getEnum(LibraryManagementOptions.class, option, description);
	}

	public static List<LibraryManagementOptions> getValues() {
		return MenuOptions.getValues(LibraryManagementOptions.class);
	}

}
