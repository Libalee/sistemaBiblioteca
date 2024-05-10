package com.biblioteca.data.model;

import java.util.Objects;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "library_item")
public class LibraryItem extends Book{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "book")
	private Book book;
	
	@Column(name = "identfication_code", length = 64, nullable = false)
	private Double identificationCode;
	
	@Column(name = "avaliable", nullable = false)
	private boolean avaliable;
	
	@Column(name = "borrowing_date", nullable = false)
	private Date borrowingDate;
	
	@Column(name = "return_date", nullable = false)
	private Date returnDate;
	
	@Column(name = "reserved", nullable = false)
	private boolean reserved;
	
	public LibraryItem() {
		
	}

	public LibraryItem(Long id, Book book, Double identificationCode, boolean avaliable, Date borrowingDate,
			Date returnDate, boolean reserved) {
		super();
		this.id = id;
		this.book = book;
		this.identificationCode = identificationCode;
		this.avaliable = avaliable;
		this.borrowingDate = borrowingDate;
		this.returnDate = returnDate;
		this.reserved = reserved;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Double getIdentificationCode() {
		return identificationCode;
	}

	public void setIdentificationCode(Double identificationCode) {
		this.identificationCode = identificationCode;
	}

	public boolean isAvaliable() {
		return avaliable;
	}

	public void setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
	}

	public Date getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(Date borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(avaliable, book, borrowingDate, id, identificationCode, reserved, returnDate);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryItem other = (LibraryItem) obj;
		return avaliable == other.avaliable && Objects.equals(book, other.book)
				&& Objects.equals(borrowingDate, other.borrowingDate) && Objects.equals(id, other.id)
				&& Objects.equals(identificationCode, other.identificationCode) && reserved == other.reserved
				&& Objects.equals(returnDate, other.returnDate);
	}
	
	
}
