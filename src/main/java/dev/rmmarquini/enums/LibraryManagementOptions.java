package dev.rmmarquini.enums;

import java.util.List;

public enum LibraryManagementOptions {

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

	public int getOption() {
		return option;
	}

	public String getDescription() {
		return description;
	}

	public static LibraryManagementOptions getEnumByOption(int option) {
		for (LibraryManagementOptions value : LibraryManagementOptions.values()) {
			if (value.getOption() == option) {
				return value;
			}
		}
		return null;
	}

	public static LibraryManagementOptions getEnumByDescription(String description) {
		for (LibraryManagementOptions value : LibraryManagementOptions.values()) {
			if (value.getDescription().equals(description)) {
				return value;
			}
		}
		return null;
	}

	public static LibraryManagementOptions getEnum(int option, String description) {
		for (LibraryManagementOptions value : LibraryManagementOptions.values()) {
			if (value.getOption() == option && value.getDescription().equals(description)) {
				return value;
			}
		}
		return null;
	}

	public static List<LibraryManagementOptions> getValues() {
		return List.of(LibraryManagementOptions.values());
	}

}
