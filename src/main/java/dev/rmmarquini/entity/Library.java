package dev.rmmarquini.entity;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

	private final List<Book> books;
	private final List<Author> authors;
	private final List<User> users;
	private final List<Loan> loans;

	public Library() {
		this.books = new LinkedList<>();
		this.authors = new LinkedList<>();
		this.users = new LinkedList<>();
		this.loans = new LinkedList<>();
	}

	public List<Book> getBooks() {
		return books;
	}

	public Book getBookByTitle(String title) {
		return this.books.stream().filter(b -> b.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
	}

	public List<Book> getBookByAuthor(String author) {
		Author authorObj = this.authors.stream().filter(a -> a.getName().equalsIgnoreCase(author)).findFirst().orElse(null);
		return authorObj != null ? this.books.stream().filter(b -> b.getAuthors().contains(authorObj)).collect(Collectors.toList()) : null;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public Author getAuthorByName(String name) {
		return this.authors.stream().filter(a -> a.getName().equals(name)).findFirst().orElse(null);
	}

	public List<User> getUsers() {
		return users;
	}

	public User getUserByName(String name) {
		return this.users.stream().filter(u -> u.getName().equals(name)).findFirst().orElse(null);
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public List<Loan> getLoansByUser(User user) {
		return this.loans.stream().filter(l -> l.getUser().equals(user)).collect(Collectors.toList());
	}

	public List<Loan> getLoansByBook(Book book) {
		return this.loans.stream()
				.map(Loan::getCheckedOutBooks)
				.filter(books -> books.contains(book))
				.map(books -> this.loans.stream().filter(l -> new HashSet<>(l.getCheckedOutBooks()).containsAll(books)).findFirst().orElse(null))
				.collect(Collectors.toList());
	}

	public List<Loan> getLoansByDate(String date) {
		return this.loans.stream().filter(l -> l.getCheckOutDate().toString().equals(date)).collect(Collectors.toList());
	}

	public static class Builder {

		private final Library library;

		public Builder() {
			this.library = new Library();
		}

		public Builder addBook(Book book) {
			this.library.books.add(book);
			return this;
		}

		public Builder updateBook(Book book) {
			int index = this.library.books.indexOf(book);
			if (index != -1) {
				this.library.books.set(index, book);
			}
			return this;
		}

		public Builder removeBook(String id) {
			this.library.books.removeIf(b -> b.getId().equals(id));
			return this;
		}

		public Builder addAuthor(Author author) {
			this.library.authors.add(author);
			return this;
		}

		public Builder updateAuthor(Author author) {
			int index = this.library.authors.indexOf(author);
			if (index != -1) {
				this.library.authors.set(index, author);
			}
			return this;
		}

		public Builder removeAuthor(String id) {
			this.library.authors.removeIf(a -> a.getId().equals(id));
			return this;
		}

		public Builder addUser(User user) {
			this.library.users.add(user);
			return this;
		}

		public Builder updateUser(User user) {
			int index = this.library.users.indexOf(user);
			if (index != -1) {
				this.library.users.set(index, user);
			}
			return this;
		}

		public Builder removeUser(String id) {
			this.library.users.removeIf(u -> u.getId().equals(id));
			return this;
		}

		public Builder addLoan(Loan loan) {
			this.library.loans.add(loan);
			return this;
		}

		public Builder updateLoan(Loan loan) {
			int index = this.library.loans.indexOf(loan);
			if (index != -1) {
				this.library.loans.set(index, loan);
			}
			return this;
		}

		public Library build() {
			return this.library;
		}

	}

}
