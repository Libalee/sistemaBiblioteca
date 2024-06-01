package com.biblioteca.testMyModelMapper;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 256)
	private String name;
	
	@Column(name = "autor", nullable = false, length = 128)
	private String autor;
	
	@Column(name = "volume", nullable = false, length = 8)
	private Integer volume;
	
	@Column(name = "avaliable", nullable = false)
	private boolean avaliable;
	
	@Column(name = "borrowing_date", nullable = false)
	private Date borrowingDate;
	
	@Column(name = "return_date", nullable = false)
	private Date returnDate;
	
	@Column(name = "reserved", nullable = false)
	private boolean reserved;

	public Book() {
		
	}

	public Book(Long id, String name, String autor, Integer volume, boolean avaliable, Date borrowingDate,
			Date returnDate, boolean reserved) {
		super();
		this.id = id;
		this.name = name;
		this.autor = autor;
		this.volume = volume;
		this.avaliable = avaliable;
		this.borrowingDate = borrowingDate;
		this.returnDate = returnDate;
		this.reserved = reserved;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
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
	
	

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, avaliable, borrowingDate, id, name, reserved, returnDate, volume);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(autor, other.autor) && avaliable == other.avaliable
				&& Objects.equals(borrowingDate, other.borrowingDate) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && reserved == other.reserved
				&& Objects.equals(returnDate, other.returnDate) && Objects.equals(volume, other.volume);
	}
	
	
	
}
