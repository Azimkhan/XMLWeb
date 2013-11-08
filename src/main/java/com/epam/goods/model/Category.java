package com.epam.goods.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Category implements Serializable, Cloneable{

	private static final long serialVersionUID = -720097598026605151L;
	private String name;
	private List<SubCategory> subCategories = new LinkedList<>();
	
	/**
	 * @param name
	 */
	public Category(String name) {
		super();
		this.name = name;
	}
	/**
	 * 
	 */
	public Category() {
		super();
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the subCategories
	 */
	public List<SubCategory> getSubCategories() {
		return subCategories;
	}
	

}
