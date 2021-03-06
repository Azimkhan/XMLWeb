package com.epam.goods.command;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.goods.model.Category;
import com.epam.goods.parser.GoodsParser;
import com.epam.goods.parser.factory.GoodsParserFactory;

public class DataCommand extends Command{

	private String filename;

	

	public DataCommand(String filename) {
		super();
		this.filename = filename;
	}



	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// Create parser factory
		GoodsParserFactory factory = new GoodsParserFactory();
		
		// Create parser. Allowed values (sax, stax, dom)
		String paserValue = request.getParameter("parser");
		GoodsParser parser = factory.create(paserValue != null ? paserValue : "stax");
		
		// Get start time
		long startTime = System.currentTimeMillis();
		List<Category> categories = parser.parse(filename);
		
		// Get end time
		long endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		
		String parserName = parser.getClass().getSimpleName();
		
		request.setAttribute("categories", categories);
		request.setAttribute("filename", filename);
		request.setAttribute("parserName", parserName);
		request.setAttribute("parseTime", time);
		
		return "data.jsp";
	}

}
