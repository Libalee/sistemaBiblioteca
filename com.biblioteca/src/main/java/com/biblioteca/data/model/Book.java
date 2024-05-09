package com.biblioteca.data.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 256)
	private String name;
	
	@Column(name = "autor", nullable = false, length = 128)
	private String autor;
	
	@Column(name = "volume", nullable = false, length = 8)
	private Integer volume;
	
	@Column(name = "identification_code", nullable = false, length = 256)
	private Double identificationCode;
	
	@Column(name = "number_of_items", nullable = false, length = 8)
	private Integer numberOfItems;

	public Book() {
		
	}
	
	public Book(Long id, String name, String autor, Integer volume, Double identificationCode, Integer numberOfItems) {
		super();
		this.id = id;
		this.name = name;
		this.autor = autor;
		this.volume = volume;
		this.identificationCode = identificationCode;
		this.numberOfItems = numberOfItems;
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

	public Double getIdentificationCode() {
		return identificationCode;
	}

	public void setIdentificationCode(Double identificationCode) {
		this.identificationCode = identificationCode;
	}

	public Integer getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(Integer numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, id, identificationCode, name, numberOfItems, volume);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(id, other.id)
				&& Objects.equals(identificationCode, other.identificationCode) && Objects.equals(name, other.name)
				&& Objects.equals(numberOfItems, other.numberOfItems) && Objects.equals(volume, other.volume);
	}
	
	
}
