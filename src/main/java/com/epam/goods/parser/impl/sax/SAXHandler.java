package com.epam.goods.parser.impl.sax;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.goods.builder.GoodsBuilder;
import com.epam.goods.model.Category;

public class SAXHandler extends DefaultHandler{
	private GoodsBuilder builder = new GoodsBuilder();
	private String nextProperty;
	
	/**
	 * @return the goods
	 */
	public List<Category> getCategories() {
		return builder.build();
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] chars, int start, int end) throws SAXException {
		if (nextProperty != null){
			String value = new String(chars, start, end).trim();
			
			switch (nextProperty) {
			case "producer":
				builder.buildProducer(value);
				break;
			case "model":
				builder.buildModel(value);
				break;
			case "color":
				builder.buildColor(value);
				break;
			case "date":
				builder.buildDate(value);
				break;
			case "notInStock":
				builder.buildNotInStock(value);
				break;
			case "price":
				builder.buildPrice(value);
				break;
			}
			
			nextProperty = null;
		}
		
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		switch (qName) {
		case "category":
			builder.createCategory(attributes.getValue("name"));
			break;
		case "subcategory":
			builder.createSubCategory(attributes.getValue("name"));
			break;
		case "goods":
			builder.createGood(attributes.getValue("name"));
			break;
		default:
			nextProperty = qName;
			break;
		}
		
	}
	
	
	
	
}
