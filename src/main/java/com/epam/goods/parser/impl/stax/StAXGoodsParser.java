package com.epam.goods.parser.impl.stax;

import java.io.FileInputStream;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.goods.exception.ParseException;
import com.epam.goods.model.Category;
import com.epam.goods.parser.GoodsParser;

public class StAXGoodsParser extends GoodsParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(StAXGoodsParser.class);
	@Override
	protected List<Category> parse(FileInputStream in) {
		
		XMLStreamReader reader = null;
		try {

			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			reader = inputFactory.createXMLStreamReader(in);
			StAXHandler handler = new StAXHandler(reader);
			
			// parse
			return handler.handle();

		} catch (XMLStreamException e) {
			throw new ParseException(e);
		} finally {
			if (reader != null){
				try {
					reader.close();
				} catch (XMLStreamException e) {
					LOGGER.error("xml reader close error: {}", e);
				}
			}
		}
	}

}
