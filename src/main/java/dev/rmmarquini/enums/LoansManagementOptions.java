package dev.rmmarquini.enums;

import java.util.List;

public enum LoansManagementOptions implements MenuOptions<LoansManagementOptions> {

	LOAN_BOOK(1, "Loan book"),
	RETURN_BOOK(2, "Return book"),
	LIST_LOANS(3, "List all loans"),
	LIST_LOANS_BY_USER(4, "List loans by user"),
	LIST_LOANS_BY_BOOK(5, "List loans by book"),
	LIST_LOANS_BY_DATE(6, "List loans by date"),
	EXIT(7, "Back to main menu");

	public final int option;
	public final String description;

	LoansManagementOptions(int option, String description) {
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

	public static LoansManagementOptions getEnumByOption(int option) {
		return MenuOptions.getEnumByOption(LoansManagementOptions.class, option);
	}

	public static LoansManagementOptions getEnumByDescription(String description) {
		return MenuOptions.getEnumByDescription(LoansManagementOptions.class, description);
	}

	public static LoansManagementOptions getEnum(int option, String description) {
		return MenuOptions.getEnum(LoansManagementOptions.class, option, description);
	}

	public static List<LoansManagementOptions> getValues() {
		return MenuOptions.getValues(LoansManagementOptions.class);
	}

}
