package com.epam.goods.parser.factory;

import com.epam.goods.parser.GoodsParser;
import com.epam.goods.parser.impl.dom.DOMGoodsParser;
import com.epam.goods.parser.impl.sax.SAXGoodsParser;
import com.epam.goods.parser.impl.stax.StAXGoodsParser;

/**
 * Factory for goods parsers
 */
public class GoodsParserFactory {
	
	/**
	 * Create and return an instance of GoodsParser by a given string
	 * @param name parser name
	 * @param filename file to parse
	 * @return parser
	 */
	public GoodsParser create(String name){
		switch (name) {
		case "sax":
			return new SAXGoodsParser();

		case "stax":
			return new StAXGoodsParser();
			
		case "dom":
			return new DOMGoodsParser();
			
		}
		
		throw new RuntimeException("Parser not found");
	}

}
