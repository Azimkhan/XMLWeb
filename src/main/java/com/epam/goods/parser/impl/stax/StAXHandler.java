package com.epam.goods.parser.impl.stax;

import java.util.List;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.epam.goods.builder.CategoriesBuilder;
import com.epam.goods.document.DocumentConstants;
import com.epam.goods.model.Category;

public class StAXHandler {
	private XMLStreamReader reader;
	private CategoriesBuilder builder = new CategoriesBuilder();
	private String nextProperty;
	
	public StAXHandler(XMLStreamReader reader){
		this.reader = reader;
	}

	/**
	 * Parse document
	 * @return categories
	 * @throws XMLStreamException
	 */
	public List<Category> handle() throws XMLStreamException {
		while(reader.hasNext()){
			int event = reader.next();
			
			switch (event) {
			case XMLStreamConstants.START_ELEMENT:
				startElement();
				break;
			case XMLStreamConstants.CHARACTERS:
				characters();
				break;
			}
			
		}
		
		return builder.build();
	}
	
	/**
	 * Handle element start
	 */
	public void startElement(){
		String localName = reader.getLocalName();
		switch (localName) {
		case DocumentConstants.TAG_CATEGORY:
			builder.createCategory(reader.getAttributeValue(0));
			break;
		case DocumentConstants.TAG_SUBCATEGORY:
			builder.createSubCategory(reader.getAttributeValue(0));
			break;
		case DocumentConstants.TAG_GOODS:
			builder.createGood(reader.getAttributeValue(0));
			break;
		default:
			nextProperty = localName;
			break;
		}
	}
	
	/**
	 * Handle characters
	 */
	public void characters(){
		if(nextProperty != null){
		String value = reader.getText().trim();
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
	

}
