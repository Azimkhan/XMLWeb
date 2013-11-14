package com.epam.goods.parser.impl.sax;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.goods.builder.CategoriesBuilder;
import com.epam.goods.document.DocumentConstants;
import com.epam.goods.model.Category;

public class SAXHandler extends DefaultHandler{
	private CategoriesBuilder builder = new CategoriesBuilder();
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
			case DocumentConstants.TAG_PRODUCER:
				builder.buildProducer(value);
				break;
			case DocumentConstants.TAG_MODEL:
				builder.buildModel(value);
				break;
			case DocumentConstants.TAG_COLOR:
				builder.buildColor(value);
				break;
			case DocumentConstants.TAG_DATE:
				builder.buildDate(value);
				break;
			case DocumentConstants.TAG_NOTINSTOCK:
				builder.buildNotInStock(value);
				break;
			case DocumentConstants.TAG_PRICE:
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
		case DocumentConstants.TAG_CATEGORY:
			builder.createCategory(attributes.getValue(DocumentConstants.ATTRIBUTE_NAME));
			break;
		case DocumentConstants.TAG_SUBCATEGORY:
			builder.createSubCategory(attributes.getValue(DocumentConstants.ATTRIBUTE_NAME));
			break;
		case DocumentConstants.TAG_GOODS:
			builder.createGood(attributes.getValue(DocumentConstants.ATTRIBUTE_NAME));
			break;
		default:
			nextProperty = qName;
			break;
		}
		
	}
	
	
	
	
}
