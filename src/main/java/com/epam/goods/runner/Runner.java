package com.epam.goods.runner;

import java.util.List;

import com.epam.goods.model.Category;
import com.epam.goods.model.Good;
import com.epam.goods.model.SubCategory;
import com.epam.goods.parser.factory.GoodsParserFactory;
import com.epam.goods.parser.iface.GoodsParser;

public class Runner {
	public static void main(String[] args){
		GoodsParserFactory factory = new GoodsParserFactory();
		GoodsParser parser = factory.create("sax", "goods.xml");
		List<Category> goods = parser.parse();
		
		for(Category cat: goods){
			for(SubCategory sub : cat.getSubCategories()){
				for(Good good : sub.getGoods()){
					System.out.println(good);
				}
			}
		}
		
	}
}
