package dev.rmmarquini.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Loan {

	private final String id;
	private final User user;
	private final List<Book> checkedOutBooks;
	private LocalDate checkOutDate;
	private LocalDate returnDate;
	private boolean returned;
	private boolean renewed;

	public Loan(String id, User user, List<Book> checkedOutBooks) {
		this.id = id;
		this.user = user;
		this.checkedOutBooks = checkedOutBooks;
		this.checkOutDate = LocalDate.now();
		this.returnDate = checkOutDate.plusDays(7);
		this.returned = false;
		this.renewed = false;
	}

	public String getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public List<Book> getCheckedOutBooks() {
		return checkedOutBooks;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public boolean isRenewed() {
		return renewed;
	}

	public void setRenewed(boolean renewed) {
		this.renewed = renewed;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Loan loan = (Loan) o;
		return returned == loan.returned && renewed == loan.renewed && Objects.equals(id, loan.id) && Objects.equals(user, loan.user) && Objects.equals(checkedOutBooks, loan.checkedOutBooks) && Objects.equals(checkOutDate, loan.checkOutDate) && Objects.equals(returnDate, loan.returnDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user, checkedOutBooks, checkOutDate, returnDate, returned, renewed);
	}

	@Override
	public String toString() {
		return "Loan{" +
				"id='" + id + '\'' +
				", user=" + user +
				", checkedOutBooks=" + checkedOutBooks +
				", checkOutDate=" + checkOutDate +
				", returnDate=" + returnDate +
				", returned=" + returned +
				", renewed=" + renewed +
				'}';
	}

}
