package com.biblioteca.data.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "professor")
public class Professor extends User{
	
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "user")
	private User user;
	
	@Column(name = "borrowing_limit", length = 8)
	private Integer borrowingLimit;
	
	@Column(name = "course_taugth", length = 64, nullable = false)
	private String courseTaught;

	public Professor() {
		
	}
	
	public Professor(User user, Integer borrowingLimit, String courseTaught) {
		super();
		this.user = user;
		this.borrowingLimit = borrowingLimit;
		this.courseTaught = courseTaught;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getBorrowingLimit() {
		return borrowingLimit;
	}

	public void setBorrowingLimit(Integer borrowingLimit) {
		this.borrowingLimit = borrowingLimit;
	}

	public String getCourseTaught() {
		return courseTaught;
	}

	public void setCourseTaught(String courseTaught) {
		this.courseTaught = courseTaught;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(borrowingLimit, courseTaught, user);
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
		Professor other = (Professor) obj;
		return Objects.equals(borrowingLimit, other.borrowingLimit) && Objects.equals(courseTaught, other.courseTaught)
				&& Objects.equals(user, other.user);
	}
	
	
}
