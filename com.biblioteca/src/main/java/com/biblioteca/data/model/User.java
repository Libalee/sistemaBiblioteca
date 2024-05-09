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
	
	@Column(name = "library_id", nullable = false, length = 128)
	private String libraryID;
	
	// TO-DO add some kind of security here
	@Column(name = "library_passoword", nullable = false)
	private String libraryPassoword;
	
	@Column(name = "items_taken")
	private List<LibraryItem> itemsTaken;
	
	@Column(name = "reserved_items")
	private List<LibraryItem> reservedItems;
	
	@Column(name = "type_of_user", nullable = false)
	private String typeOfUser;

	public User() {
		
	}
	
	
	
	public User(Long id, String name, String libraryID, String libraryPassoword, List<LibraryItem> itemsTaken,
			List<LibraryItem> reservedItems, String typeOfUser) {
		super();
		this.id = id;
		this.name = name;
		this.libraryID = libraryID;
		this.libraryPassoword = libraryPassoword;
		this.itemsTaken = itemsTaken;
		this.reservedItems = reservedItems;
		this.typeOfUser = typeOfUser;
	}


	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<LibraryItem> getItemsTaken() {
		return itemsTaken;
	}

	public void setItemsTaken(List<LibraryItem> itemsTaken) {
		this.itemsTaken = itemsTaken;
	}

	public List<LibraryItem> getReservedItems() {
		return reservedItems;
	}

	public void setReservedItems(List<LibraryItem> reservedItems) {
		this.reservedItems = reservedItems;
	}

	public String getTypeOfUser() {
		return typeOfUser;
	}


	public void setTypeOfUser(String typeOfUser) {
		this.typeOfUser = typeOfUser;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, itemsTaken, libraryID, libraryPassoword, name, reservedItems, typeOfUser);
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
		return Objects.equals(id, other.id) && Objects.equals(itemsTaken, other.itemsTaken)
				&& Objects.equals(libraryID, other.libraryID)
				&& Objects.equals(libraryPassoword, other.libraryPassoword) && Objects.equals(name, other.name)
				&& Objects.equals(reservedItems, other.reservedItems) && Objects.equals(typeOfUser, other.typeOfUser);
	}
	
	
	
}
