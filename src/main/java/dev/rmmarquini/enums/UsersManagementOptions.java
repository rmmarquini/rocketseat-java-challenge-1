package dev.rmmarquini.enums;

import java.util.List;

public enum UsersManagementOptions implements MenuOptions<UsersManagementOptions> {

	ADD_USER(1, "Add new user"),
	UPDATE_USER(2, "Update user"),
	DELETE_USER(3, "Delete user"),
	LIST_USERS(4, "List users"),
	SEARCH_USER_BY_NAME(5, "Search user by name"),
	EXIT(6, "Back to main menu");

	public final int option;
	public final String description;

	UsersManagementOptions(int option, String description) {
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

	public static UsersManagementOptions getEnumByOption(int option) {
		return MenuOptions.getEnumByOption(UsersManagementOptions.class, option);
	}

	public static UsersManagementOptions getEnumByDescription(String description) {
		return MenuOptions.getEnumByDescription(UsersManagementOptions.class, description);
	}

	public static UsersManagementOptions getEnum(int option, String description) {
		return MenuOptions.getEnum(UsersManagementOptions.class, option, description);
	}

	public static List<UsersManagementOptions> getValues() {
		return MenuOptions.getValues(UsersManagementOptions.class);
	}

}
