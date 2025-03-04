package dev.rmmarquini.enums;

import java.util.EnumSet;
import java.util.List;

public interface MenuOptions<T extends Enum<T>> {

	int getOption();
	String getDescription();

	static <T extends Enum<T> & MenuOptions<T>> T getEnumByOption(Class<T> enumClass, int option) {
		return EnumSet.allOf(enumClass).stream()
				.filter(value -> value.getOption() == option)
				.findFirst()
				.orElse(null);
	}

	static <T extends Enum<T> & MenuOptions<T>> T getEnumByDescription(Class<T> enumClass, String description) {
		return EnumSet.allOf(enumClass).stream()
				.filter(value -> value.getDescription().equals(description))
				.findFirst()
				.orElse(null);
	}

	static <T extends Enum<T> & MenuOptions<T>> T getEnum(Class<T> enumClass, int option, String description) {
		return EnumSet.allOf(enumClass).stream()
				.filter(value -> value.getOption() == option && value.getDescription().equals(description))
				.findFirst()
				.orElse(null);
	}

	static <T extends Enum<T> & MenuOptions<T>> List<T> getValues(Class<T> enumClass) {
		return List.copyOf(EnumSet.allOf(enumClass));
	}

}
