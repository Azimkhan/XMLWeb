package com.epam.goods.parser.impl.dom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.epam.goods.exception.ParseException;
import com.epam.goods.model.Category;
import com.epam.goods.parser.GoodsParser;

public class DOMGoodsParser extends GoodsParser{

	protected List<Category> parse(FileInputStream in) throws ParseException {

		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document document = builder.parse(in);
			DOMHandler handler = new DOMHandler(document);
			
			return handler.handle();
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new ParseException(e);
		} 
		
	}
}
