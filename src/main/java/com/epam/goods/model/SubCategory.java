package com.epam.goods.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SubCategory implements Serializable, Cloneable{
	
	private static final long serialVersionUID = -4142165936748096913L;
	private String name;
	private List<Good> goods = new LinkedList<>();
	
	/**
	 * @param name
	 */
	public SubCategory(String name) {
		super();
		this.name = name;
	}
	/**
	 * 
	 */
	public SubCategory() {
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
	 * @return the goods
	 */
	public List<Good> getGoods() {
		return goods;
	}
	
	
	
	
}
