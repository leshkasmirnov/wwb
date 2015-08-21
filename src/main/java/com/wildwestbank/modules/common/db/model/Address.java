/**
 * 
 */
package com.wildwestbank.modules.common.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author Alexey Smirnov <aleksey.smirnov89@gmail.com>
 *
 */
@Entity
public class Address implements Identified, Serializable {

	private static final long serialVersionUID = 8700832184667466581L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresses_sequence")
	@SequenceGenerator(sequenceName = "addresses_sequence", name = "addresses_sequence", allocationSize = 1)
	private Integer id;

	private String region;
	private String city;
	private String street;
	private String house;
	private String building;
	private String flat;
	@Column(name = "zip_code")
	private String zipCode;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String asText() {
		StringBuilder sb = new StringBuilder();
		if (region != null) {
			sb.append(region);
		}
		if (city != null) {
			sb.append(", г. " + city);
		}
		if (street != null) {
			sb.append(", ул. " + street);
		}
		if (house != null) {
			sb.append(", д. " + house);
		}
		if (building != null) {
			sb.append(", корп. " + building);
		}
		if (flat != null) {
			sb.append(", кв. " + flat);
		}
		if (zipCode != null) {
			sb.append(", индекс " + zipCode);
		}
		return sb.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
