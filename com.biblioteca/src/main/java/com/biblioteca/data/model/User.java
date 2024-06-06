package com.biblioteca.data.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 128)
	private String name;
	
	@Column(name = "library_id", nullable = false, unique = true, length = 128)
	private String libraryID;
	
	// TO DO add some kind of security here
	@Column(name = "library_passoword", nullable = false)
	private String libraryPassoword;
	
	@Column(name = "items_taken")
	private List<Book> itemsTaken;
	
	@Column(name = "reserved_items")
	private List<Book> reservedItems;
	
	@Column(name = "fine_value", length = 16)
	private Double fineValue; // fine as in a bill, something you have to pay
	

	public User() {
		
	}


	public User(Long id, String name, String libraryID, String libraryPassoword, List<Book> itemsTaken,
			List<Book> reservedItems, Double fineValue) {
		super();
		this.id = id;
		this.name = name;
		this.libraryID = libraryID;
		this.libraryPassoword = libraryPassoword;
		this.itemsTaken = itemsTaken;
		this.reservedItems = reservedItems;
		this.fineValue = fineValue;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public String getFirstName() {
		return getName().split(" ")[0];
	}
	
	public String getLastName() {
		return getName().split(" ", 2)[1];
	}


	public String getLibraryID() {
		return libraryID;
	}


	public void setLibraryID(String libraryID) {
		this.libraryID = libraryID;
	}


	public String getLibraryPassoword() {
		return libraryPassoword;
	}


	public void setLibraryPassoword(String libraryPassoword) {
		this.libraryPassoword = libraryPassoword;
	}


	public List<Book> getItemsTaken() {
		return itemsTaken;
	}


	public void setItemsTaken(List<Book> itemsTaken) {
		this.itemsTaken = itemsTaken;
	}


	public List<Book> getReservedItems() {
		return reservedItems;
	}


	public void setReservedItems(List<Book> reservedItems) {
		this.reservedItems = reservedItems;
	}


	public Double getFineValue() {
		return fineValue;
	}


	public void setFineValue(Double fineValue) {
		this.fineValue = fineValue;
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
		return Objects.hash(fineValue, id, itemsTaken, libraryID, libraryPassoword, name, reservedItems);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(fineValue, other.fineValue) && Objects.equals(id, other.id)
				&& Objects.equals(itemsTaken, other.itemsTaken) && Objects.equals(libraryID, other.libraryID)
				&& Objects.equals(libraryPassoword, other.libraryPassoword) && Objects.equals(name, other.name)
				&& Objects.equals(reservedItems, other.reservedItems);
	}
	
}
