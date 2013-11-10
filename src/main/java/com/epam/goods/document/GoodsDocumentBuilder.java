package com.epam.goods.document;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.epam.goods.model.Category;
import com.epam.goods.model.SubCategory;
import com.epam.goods.model.Good;


public class GoodsDocumentBuilder {
	private Document document;
	private Element rootElement;
	private Element categoryElement;
	private Element subCategoryElement;
	private Element goodElement;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public GoodsDocumentBuilder(Document document) {
		super();
		this.document = document;
	}

	public Document build(List<Category> categories){

		try {
			if (document == null){
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = dbf.newDocumentBuilder();
				document = builder.newDocument();
				rootElement = document.createElement("categories");
				document.appendChild(rootElement);
				rootElement.setAttribute("xmlns", "http://epam.com/goods");
				rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
				rootElement.setAttribute("xsi:schemaLocation", "http://epam.com/goods schema.xsd");
			} else{
				rootElement = document.getDocumentElement();
			}
			
			for(Category category: categories){
				buildCategory(category);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return document;
		
	}
	
	private void buildCategory(Category category){
		categoryElement = document.createElement("category");
		categoryElement.setAttribute("name", category.getName());
		rootElement.appendChild(categoryElement);
		
		for(SubCategory subCategory : category.getSubCategories()){
			buildSubCategory(subCategory);
		}
	}
	
	public void buildSubCategory(SubCategory subCategory){
		subCategoryElement = document.createElement("subcategory");
		subCategoryElement.setAttribute("name", subCategory.getName());
		categoryElement.appendChild(subCategoryElement);
		
		for(Good good : subCategory.getGoods()){
			buildGood(good);
		}
	}
	
	public void buildGood(Good good){
		goodElement = document.createElement("goods");
		goodElement.setAttribute("name", good.getName());
		
		subCategoryElement.appendChild(goodElement);
		
		Element producerElement = document.createElement("producer");
		producerElement.appendChild(document.createTextNode(good.getProducer()));
		goodElement.appendChild(producerElement);
		
		Element modelElement = document.createElement("model");
		modelElement.appendChild(document.createTextNode(good.getModel()));
		goodElement.appendChild(modelElement);
		
		Element dateElement = document.createElement("date");
		dateElement.appendChild(document.createTextNode(dateFormat.format(good.getDate().getTime())));
		goodElement.appendChild(dateElement);
		
		Element colorElement = document.createElement("color");
		colorElement.appendChild(document.createTextNode(good.getColor().toString().toLowerCase()));
		goodElement.appendChild(colorElement);
		
		if (!good.isNotInStock()){
			Element priceElement = document.createElement("price");
			priceElement.appendChild(document.createTextNode(String.valueOf(good.getPrice())));
			goodElement.appendChild(priceElement);
		} else {
			Element noInStockElement = document.createElement("notInStock");
			noInStockElement.appendChild(document.createTextNode("true"));
			goodElement.appendChild(noInStockElement);
		}
		
		
	}
}
