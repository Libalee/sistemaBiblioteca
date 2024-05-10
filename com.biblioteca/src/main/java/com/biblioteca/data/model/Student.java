package com.biblioteca.data.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends User{
	
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "user")
	private User user;
	
	@Column(name = "student_id", length = 16)
	private Integer studentID;
	
	@Column(name = "fine_value", length = 16)
	private Double fineValue; // fine as in a bill, something you have to pay
	
	public Student() {
		
	}
	
	public Student(User user, Integer studentID, Double fineValue) {
		super();
		this.user = user;
		this.studentID = studentID;
		this.fineValue = fineValue;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public Double getFineValue() {
		return fineValue;
	}

	public void setFineValue(Double fineValue) {
		this.fineValue = fineValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(fineValue, studentID, user);
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
		Student other = (Student) obj;
		return Objects.equals(fineValue, other.fineValue) && Objects.equals(studentID, other.studentID)
				&& Objects.equals(user, other.user);
	}
	
	
}
