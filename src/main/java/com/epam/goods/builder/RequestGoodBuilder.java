package com.epam.goods.builder;

import javax.servlet.http.HttpServletRequest;

import com.epam.goods.document.DocumentConstants;
import com.epam.goods.exception.BuildException;
import com.epam.goods.model.Good;

/**
 * Create good object from request
 * @author azimkhan
 *
 */
public class RequestGoodBuilder extends GoodBuilder{
	
	
	
	public Good build(HttpServletRequest request) throws BuildException{
		String name = request.getParameter(DocumentConstants.ATTRIBUTE_NAME);
		String producer = request.getParameter(DocumentConstants.TAG_PRODUCER);
		String model = request.getParameter(DocumentConstants.TAG_MODEL);
		String date = request.getParameter(DocumentConstants.TAG_DATE);
		String color = request.getParameter(DocumentConstants.TAG_COLOR);
		String price = request.getParameter(DocumentConstants.TAG_PRICE);
		String notInStock = request.getParameter(DocumentConstants.TAG_NOTINSTOCK);
		
		createGood(name);
		buildProducer(producer);
		buildModel(model);
		buildColor(color);
		buildDate(date);
		buildNotInStock(notInStock);
		buildPrice(price);
		
		return getGood();
	}
}
