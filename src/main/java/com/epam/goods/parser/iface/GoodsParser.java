package com.epam.goods.parser.iface;

import java.util.List;

import com.epam.goods.model.Category;


public interface GoodsParser {
	List<Category> parse();
}
