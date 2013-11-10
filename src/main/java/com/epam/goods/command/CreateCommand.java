package com.epam.goods.command;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.epam.goods.builder.GoodsBuilder;
import com.epam.goods.document.GoodsDocumentBuilder;
import com.epam.goods.model.Category;
import com.epam.goods.model.Good;

public class CreateCommand implements Command{

	private String filename;
	
	public CreateCommand(String filename) {
		super();
		this.filename = filename;
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		if (request.getParameter("submit") != null){
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document document = builder.parse(filename);
			GoodsDocumentBuilder docBuilder = new GoodsDocumentBuilder(document);
			GoodsBuilder gBuilder = new GoodsBuilder();
			
			String producer = request.getParameter("producer");
			String model = request.getParameter("model");
			String date = request.getParameter("date");
			String color = request.getParameter("color");
			String price = request.getParameter("price");
			String notInStock = request.getParameter("notInStock");
			
			String name = producer + " " + model;
			
			gBuilder.createCategory("test");
			gBuilder.createSubCategory("test");
			gBuilder.createGood(name);
			gBuilder.buildProducer(producer);
			gBuilder.buildModel(model);
			gBuilder.buildDate(date);
			gBuilder.buildColor(color);
			if (!"true".equalsIgnoreCase(notInStock)){
				gBuilder.buildPrice(price);
			} else {
				gBuilder.buildNotInStock("true");
			}
			
			List<Category> categories = gBuilder.build();
			DOMSource source = new DOMSource(docBuilder.build(categories));
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			StreamResult result = new StreamResult(new File(filename));
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			t.transform(source, result);
		}
		
		return "create.jsp";
	}

}
