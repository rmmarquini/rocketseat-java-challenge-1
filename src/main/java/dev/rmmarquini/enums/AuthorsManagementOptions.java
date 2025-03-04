package dev.rmmarquini.enums;

import java.util.List;

public enum AuthorsManagementOptions implements MenuOptions<AuthorsManagementOptions> {

	ADD_AUTHOR(1, "Add new author"),
	UPDATE_AUTHOR(2, "Update author"),
	REMOVE_AUTHOR(3, "Remove author"),
	LIST_AUTHORS(4, "List authors"),
	SEARCH_AUTHOR_BY_NAME(5, "Search author by name"),
	EXIT(6, "Back to main menu");

	public final int option;
	public final String description;

	AuthorsManagementOptions(int option, String description) {
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

	public static AuthorsManagementOptions getEnumByOption(int option) {
		return MenuOptions.getEnumByOption(AuthorsManagementOptions.class, option);
	}

	public static AuthorsManagementOptions getEnumByDescription(String description) {
		return MenuOptions.getEnumByDescription(AuthorsManagementOptions.class, description);
	}

	public static AuthorsManagementOptions getEnum(int option, String description) {
		return MenuOptions.getEnum(AuthorsManagementOptions.class, option, description);
	}

	public static List<AuthorsManagementOptions> getValues() {
		return MenuOptions.getValues(AuthorsManagementOptions.class);
	}

}
