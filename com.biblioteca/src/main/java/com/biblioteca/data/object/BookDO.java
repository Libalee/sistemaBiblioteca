package com.biblioteca.data.object;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDO extends RepresentationModel<BookDO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long key;
	private String name;
	private String autor;
	private Integer volume;
	private boolean avaliable;
	@JsonProperty("borrowing_date")
	private Date borrowingDate;
	@JsonProperty("return_date")
	private Date returnDate;
	private boolean reserved;

	public BookDO() {
		
	}

	public BookDO(Long key, String name, String autor, Integer volume, boolean avaliable, Date borrowingDate,
			Date returnDate, boolean reserved) {
		super();
		this.key = key;
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

	public Long getKey() {
		return key;
	}

	
	
	public void setKey(Long key) {
		this.key = key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(autor, avaliable, borrowingDate, key, name, reserved, returnDate, volume);
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
		BookDO other = (BookDO) obj;
		return Objects.equals(autor, other.autor) && avaliable == other.avaliable
				&& Objects.equals(borrowingDate, other.borrowingDate) && Objects.equals(key, other.key)
				&& Objects.equals(name, other.name) && reserved == other.reserved
				&& Objects.equals(returnDate, other.returnDate) && Objects.equals(volume, other.volume);
	}

	
	
}
