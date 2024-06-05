package com.biblioteca.data.object;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDO extends RepresentationModel<UserDO> implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long key;
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("library_id")
	private String libraryID;
	// TO DO add some kind of security here
	@JsonProperty("library_password")
	private String libraryPassoword;
	@JsonProperty("items_taken")
	private List<BookDO> itemsTaken;
	@JsonProperty("reserved_items")
	private List<BookDO> reservedItems;
	@JsonProperty("fine_value")
	private Double fineValue; // fine as in a bill, something you have to pay
	

	public UserDO() {
		
	}

	public UserDO(Long key, String firstName, String lastName, String libraryID, String libraryPassoword,
			List<BookDO> itemsTaken, List<BookDO> reservedItems, Double fineValue) {
		super();
		this.key = key;
		this.firstName = firstName;
		this.lastName = lastName;
		this.libraryID = libraryID;
		this.libraryPassoword = libraryPassoword;
		this.itemsTaken = itemsTaken;
		this.reservedItems = reservedItems;
		this.fineValue = fineValue;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return getFirstName().concat(" " + getLastName());
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


	public List<BookDO> getItemsTaken() {
		return itemsTaken;
	}


	public void setItemsTaken(List<BookDO> itemsTaken) {
		this.itemsTaken = itemsTaken;
	}


	public List<BookDO> getReservedItems() {
		return reservedItems;
	}


	public void setReservedItems(List<BookDO> reservedItems) {
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
		result = prime * result + Objects.hash(fineValue, firstName, itemsTaken, key, lastName, libraryID,
				libraryPassoword, reservedItems);
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
		UserDO other = (UserDO) obj;
		return Objects.equals(fineValue, other.fineValue) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(itemsTaken, other.itemsTaken) && Objects.equals(key, other.key)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(libraryID, other.libraryID)
				&& Objects.equals(libraryPassoword, other.libraryPassoword)
				&& Objects.equals(reservedItems, other.reservedItems);
	}

	

	
}
