package com.epam.goods.builder;

import java.util.LinkedList;
import java.util.List;
import com.epam.goods.model.Category;
import com.epam.goods.model.Good;
import com.epam.goods.model.SubCategory;

/**
 * Builds goods object hierarchy
 */
public class CategoriesBuilder {
	
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
	
	private GoodBuilder builder = new GoodBuilder();
	

	
	public void createCategory(String name) {

		category = new Category(name);
		categories.add(category);
	}

	public void createSubCategory(String name) {

		subCategory = new SubCategory(name);
		category.getSubCategories().add(subCategory);

	}

	/**
	 * @param name
	 * @see com.epam.goods.builder.GoodBuilder#createGood(java.lang.String)
	 */
	public void createGood(String name) {
		builder.createGood(name);
		subCategory.getGoods().add(builder.getGood());
	}

	/**
	 * @param producer
	 * @see com.epam.goods.builder.GoodBuilder#buildProducer(java.lang.String)
	 */
	public void buildProducer(String producer) {
		builder.buildProducer(producer);
	}

	/**
	 * @param model
	 * @see com.epam.goods.builder.GoodBuilder#buildModel(java.lang.String)
	 */
	public void buildModel(String model) {
		builder.buildModel(model);
	}

	/**
	 * @param date
	 * @see com.epam.goods.builder.GoodBuilder#buildDate(java.lang.String)
	 */
	public void buildDate(String date) {
		builder.buildDate(date);
	}

	/**
	 * @param color
	 * @see com.epam.goods.builder.GoodBuilder#buildColor(java.lang.String)
	 */
	public void buildColor(String color) {
		builder.buildColor(color);
	}

	/**
	 * @param price
	 * @see com.epam.goods.builder.GoodBuilder#buildPrice(java.lang.String)
	 */
	public void buildPrice(String price) {
		builder.buildPrice(price);
	}

	/**
	 * @param notInStock
	 * @see com.epam.goods.builder.GoodBuilder#buildNotInStock(java.lang.String)
	 */
	public void buildNotInStock(String notInStock) {
		builder.buildNotInStock(notInStock);
	}

	/**
	 * @return
	 * @see com.epam.goods.builder.GoodBuilder#getGood()
	 */
	public Good getGood() {
		return builder.getGood();
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

	

	public List<Category> build() {

		return categories;
	}
}
