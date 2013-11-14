package com.epam.goods.parser.impl.dom;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.epam.goods.builder.CategoriesBuilder;
import com.epam.goods.document.DocumentConstants;
import com.epam.goods.model.Category;

/**
 * DOM parser for goods
 */
public class DOMHandler {
	private Document document;
	private CategoriesBuilder builder = new CategoriesBuilder();

	/**
	 * @param document
	 */
	public DOMHandler(Document document) {
		super();
		this.document = document;
	}

	/**
	 * parse
	 * @return
	 */
	public List<Category> handle() {
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node cNode = nodeList.item(i);
			if (cNode.getNodeType() == Node.ELEMENT_NODE){
				parseCategory(cNode);
			}
		}
		
		return builder.build();
	}
	
	/**
	 * Parse category
	 * @param categoryNode
	 */
	public void parseCategory(Node categoryNode){
		
		builder.createCategory(categoryNode.getAttributes().getNamedItem(DocumentConstants.ATTRIBUTE_NAME).getNodeValue());
		NodeList nodeList = categoryNode.getChildNodes();
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node cNode = nodeList.item(i);
			if (cNode.getNodeType() == Node.ELEMENT_NODE){
				parseSubCategory(cNode);
			}
		}
	}
	
	/**
	 * Parse sub category
	 * @param subCategoryNode
	 */
	public void parseSubCategory(Node subCategoryNode){
		builder.createSubCategory(subCategoryNode.getAttributes().getNamedItem(DocumentConstants.ATTRIBUTE_NAME).getNodeValue());
		NodeList nodeList = subCategoryNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node cNode = nodeList.item(i);
			if (cNode.getNodeType() == Node.ELEMENT_NODE){
				parseGood(cNode);
			}
		}
	}
	
	/**
	 * Parse goods
	 * @param goodsNode
	 */
	public void parseGood(Node goodsNode){
		builder.createGood(goodsNode.getAttributes().getNamedItem(DocumentConstants.ATTRIBUTE_NAME).getNodeValue());
		NodeList nodeList = goodsNode.getChildNodes();
		
		for(int i = 0; i < nodeList.getLength(); i++ ){
			Node cNode = nodeList.item(i);
			
			if (cNode.getNodeType() == Node.ELEMENT_NODE){
				String value = cNode.getTextContent().trim();
				
				switch (cNode.getNodeName()) {
				
				case DocumentConstants.TAG_PRODUCER:
					builder.buildProducer(value);
					break;
				case DocumentConstants.TAG_MODEL:
					builder.buildModel(value);
					break;
				case DocumentConstants.TAG_COLOR:
					builder.buildColor(value);
					break;
				case DocumentConstants.TAG_DATE:
					builder.buildDate(value);
					break;
				case DocumentConstants.TAG_NOTINSTOCK:
					builder.buildNotInStock(value);
					break;
				case DocumentConstants.TAG_PRICE:
					builder.buildPrice(value);
					break;
				
				}
			}
		}
	}
}
