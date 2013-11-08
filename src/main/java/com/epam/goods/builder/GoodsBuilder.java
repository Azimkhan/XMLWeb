package com.epam.goods.builder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import com.epam.goods.exception.BuildException;
import com.epam.goods.model.Category;
import com.epam.goods.model.Color;
import com.epam.goods.model.Good;
import com.epam.goods.model.SubCategory;

/**
 * Builds goods object hierarchy
 */
public class GoodsBuilder {
	/**
	 * List of categories
	 */
	private List<Category> categories = new LinkedList<>();
	
	/**
	 * Current category
	 */
	private Category category;
	
	/**
	 * Current sub category
	 */
	private SubCategory subCategory;
	
	/**
	 * Current good
	 */
	private Good good;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	
	public void createCategory(String name) {

		category = new Category(name);
		categories.add(category);
	}

	public void createSubCategory(String name) {

		subCategory = new SubCategory(name);
		category.getSubCategories().add(subCategory);

	}

	public void createGood(String name) {

		good = new Good(name);
		subCategory.getGoods().add(good);
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @return the subCategory
	 */
	public SubCategory getSubCategory() {
		return subCategory;
	}

	/**
	 * @return the good
	 */
	public Good getGood() {
		return good;
	}

	public void buildProducer(String producer) {
		good.setProducer(producer);
	}

	public void buildModel(String model) {
		good.setModel(model);
	}

	public void buildDate(String date) {
		Calendar c = GregorianCalendar.getInstance();
		try {
			Date d = dateFormat.parse(date);
			
			c.setTime(d);
		} catch (ParseException e) {
			throw new BuildException(e);
		}
		good.setDate(c);
	}

	public void buildColor(String color) {

		good.setColor(Color.valueOf(color.toUpperCase()));
	}

	public void buildPrice(String price) {
		good.setPrice(Integer.parseInt(price));
	}

	public void buildNotInStock(String notInStock) {
		if ("true".equalsIgnoreCase(notInStock)) {
			good.setNotInStock(true);
		} else {
			good.setNotInStock(false);
		}
	}

	public List<Category> build() {

		return categories;
	}
}
