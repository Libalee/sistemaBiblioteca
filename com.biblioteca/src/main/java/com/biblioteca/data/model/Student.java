package com.biblioteca.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends User{
	
	private static final long serialVersionUID = 1L;
	
	private Integer studentID;
	private Double fineVallue; // fine as in a bill, something you have to pay
}
