package com.biblioteca.data.model;

import java.time.LocalDateTime;

public class LibraryItem extends Book{

	private Double identificationCode;
	private boolean avaliable;
	private LocalDateTime borrowingDate;
	private LocalDateTime returnDate;
	private boolean reserved;
}
