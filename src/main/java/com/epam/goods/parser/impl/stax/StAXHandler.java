package com.epam.goods.parser.impl.stax;

import java.util.List;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.epam.goods.builder.GoodsBuilder;
import com.epam.goods.model.Category;

public class StAXHandler {
	private XMLStreamReader reader;
	private GoodsBuilder builder = new GoodsBuilder();
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
		case "category":
			builder.createCategory(reader.getAttributeValue(0));
			break;
		case "subcategory":
			builder.createSubCategory(reader.getAttributeValue(0));
			break;
		case "goods":
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
	

}
