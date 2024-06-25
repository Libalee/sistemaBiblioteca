package com.biblioteca.data.object;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
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
	
	private Double dailyFineValue = 1.0; // The value of the fine for late books.
	

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

	// Check if there are changes made to the bookDO returned to see if the operation was successful
	public List<BookDO> borrowBook(List<BookDO> bookDOs) {
		
		for(BookDO bookDO: bookDOs) {
			
			// Checks if the book is reserved or if it's reserved by this user
			boolean reservedByUser = !(bookDO.isReserved()) || this.reservedItems.contains(bookDO);
			
			if(bookDO.isAvaliable() && reservedByUser) {
				List<BookDO> list = getItemsTaken();
				list.add(bookDO);
				setItemsTaken(list);
				
				bookDO.setAvaliable(false);
				bookDO.setReserved(false);
				// getting current system time to add to the borrowing date
				Long millis = System.currentTimeMillis();
				Date date = new Date(millis);
				bookDO.setBorrowingDate(date);
				// return date is 15 days after borrowing
				Date date2 = new Date(millis + 1296000000);
				bookDO.setReturnDate(date2);
				
				bookDO.setUserWithTheBook(this);
			}
		}
		
		return bookDOs;
	}
	
	// Check if there are changes made to the bookDO returned to see if the operation was successful
	public List<BookDO> returnBook(List<BookDO> bookDOs) {
		// verifies if any of the itemsTaken's objects match with the given object
		
		List<BookDO> returnedBooks = new ArrayList<>();
		
		for(BookDO bookDO: this.itemsTaken) {
			if(!(bookDOs.contains(bookDO))) {
				bookDO.setAvaliable(true);
				bookDO.setBorrowingDate(null);
				bookDO.setUserWithTheBook(null);
				
				Long millis = System.currentTimeMillis();
				Date date = new Date(millis);
				// Checking if the return date is due
				if(bookDO.getReturnDate().before(date)) {
					this.fineValue = this.dailyFineValue * bookDO.getReturnDate().compareTo(date);
					bookDO.setReturnDate(null);
				} else {
					bookDO.setReturnDate(null);
				}
				returnedBooks.add(bookDO);
			}
		}
		this.itemsTaken.removeAll(returnedBooks);
		
		return returnedBooks;
	}
	
	// Check if there are changes made to the bookDO returned to see if the operation was successful
	public List<BookDO> reserveBook(List<BookDO> bookDOs) {
		// Checks if the bookDOs in bookDOs isn't reserved
		for(BookDO bookDO: bookDOs) {
			if(!(bookDO.isReserved())) {
				this.reservedItems.add(bookDO);
				bookDO.setReserved(true);
			}
		}
		
		return bookDOs;
	}
	
	// Check if there are changes made to the BookDO returned to see if the operation was successful
	public List<BookDO> unReserveBook(List<BookDO> bookDOs) {
		List<BookDO> list = new ArrayList<>();
		// verifies if any of the list's objects match with the given object
		for(BookDO reservedItem: this.reservedItems) {
			if(!(bookDOs.contains(reservedItem))) {
				reservedItem.setReserved(false);
				list.add(reservedItem);
			}
		}
		this.reservedItems.removeAll(list);
		return list;
	}
	
}
