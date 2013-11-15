package com.epam.goods.parser.impl.sax;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.epam.goods.exception.ParseException;
import com.epam.goods.model.Category;
import com.epam.goods.parser.GoodsParser;

public class SAXGoodsParser extends GoodsParser {

	

	protected List<Category> parse(FileInputStream in) throws ParseException {

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			
			parser.parse(in, handler);
			return handler.getCategories();
			
		} catch (IOException | SAXException | ParserConfigurationException e) {
			throw new ParseException(e);
			
		} 
	}

}
