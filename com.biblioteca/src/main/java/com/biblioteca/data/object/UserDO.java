package com.biblioteca.data.object;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UserDO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String libraryID;
	// TO DO add some kind of security here
	private String libraryPassoword;
	private List<BookDO> itemsTaken;
	private List<BookDO> reservedItems;
	private Double fineValue; // fine as in a bill, something you have to pay
	

	public UserDO() {
		
	}


	public UserDO(Long id, String name, String libraryID, String libraryPassoword, List<BookDO> itemsTaken,
			List<BookDO> reservedItems, Double fineValue) {
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


	public Long getId() {
		return id;
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
		UserDO other = (UserDO) obj;
		return Objects.equals(fineValue, other.fineValue) && Objects.equals(id, other.id)
				&& Objects.equals(itemsTaken, other.itemsTaken) && Objects.equals(libraryID, other.libraryID)
				&& Objects.equals(libraryPassoword, other.libraryPassoword) && Objects.equals(name, other.name)
				&& Objects.equals(reservedItems, other.reservedItems);
	}
	
	
	
}
