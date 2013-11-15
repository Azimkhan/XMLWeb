package com.epam.goods.document;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.goods.builder.BuilderMessages;
import com.epam.goods.builder.GoodBuilder;
import com.epam.goods.exception.BuildException;
import com.epam.goods.model.Good;

/**
 * Manipulate document nodes
 * 
 * @author azimkhan
 */
public class DocumentWriter {

	/**
	 * Target document
	 */
	private String filename;
	
	private static final ReentrantLock lock = new ReentrantLock();

	public DocumentWriter(String filename) {
		super();
		this.filename = filename;
	}

	/**
	 * Create or find category, subcategory and create goods node from goods
	 * object
	 * 
	 * @param categoryName
	 * @param subCategoryName
	 * @param good
	 *            goods object
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws TransformerException 
	 */
	public void write(String categoryName, String subCategoryName, Good good) throws ParserConfigurationException, SAXException, IOException, TransformerException {

		
		try{
			lock.lock();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document document = builder.parse(filename);
			
			Node subCategoryNode = findOrCreateSubCategoryNode(document, categoryName, subCategoryName);
			Node goodNode = createGoodNode(document, good);
			subCategoryNode.appendChild(goodNode);
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "2");
			
			// Write document to the file
			StreamResult result = new StreamResult(new File(filename));
			// Create DOM source
			DOMSource source = new DOMSource(document);
			
			transformer.transform(source, result);
			
		} finally {
			lock.unlock();
		}

	}

	/**
	 * Create a node from good object
	 * 
	 * @param good
	 * @return goods node
	 */
	private Node createGoodNode(Document document, Good good) {
		Element goodElement = document
				.createElement(DocumentConstants.TAG_GOODS);
		goodElement.setAttribute(DocumentConstants.ATTRIBUTE_NAME,
				good.getName());

		Element producerElement = document
				.createElement(DocumentConstants.TAG_PRODUCER);
		producerElement
				.appendChild(document.createTextNode(good.getProducer()));
		goodElement.appendChild(producerElement);

		Element modelElement = document
				.createElement(DocumentConstants.TAG_MODEL);
		modelElement.appendChild(document.createTextNode(good.getModel()));
		goodElement.appendChild(modelElement);

		Element dateElement = document
				.createElement(DocumentConstants.TAG_DATE);
		dateElement.appendChild(document.createTextNode(GoodBuilder.dateFormat
				.format(good.getDate().getTime())));
		goodElement.appendChild(dateElement);

		Element colorElement = document
				.createElement(DocumentConstants.TAG_COLOR);
		colorElement.appendChild(document.createTextNode(good.getColor()
				.toString().toLowerCase()));
		goodElement.appendChild(colorElement);

		if (!good.isNotInStock()) {
			Element priceElement = document
					.createElement(DocumentConstants.TAG_PRICE);
			priceElement.appendChild(document.createTextNode(String
					.valueOf(good.getPrice())));
			goodElement.appendChild(priceElement);
		} else {
			Element noInStockElement = document
					.createElement(DocumentConstants.TAG_NOTINSTOCK);
			noInStockElement.appendChild(document.createTextNode("true"));
			goodElement.appendChild(noInStockElement);
		}

		return goodElement;
	}

	/**
	 * Finds or creates subcategory node
	 * 
	 * @param categoryName
	 * @param subCategoryName
	 * @return subcategory node
	 */
	private Node findOrCreateSubCategoryNode(Document document, String categoryName,
			String subCategoryName) {

		Node categoryNode = findOrCreateCategoryNode(document, categoryName);
		if (subCategoryName != null && subCategoryName.trim().length() > 0) {
			NodeList list = categoryNode.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node cNode = list.item(i);
				if (cNode.getNodeType() == Node.ELEMENT_NODE
						&& subCategoryName.equalsIgnoreCase(cNode
								.getAttributes()
								.getNamedItem(DocumentConstants.ATTRIBUTE_NAME)
								.getNodeValue())) {
					return cNode;
				}
			}

			Element el = document
					.createElement(DocumentConstants.TAG_SUBCATEGORY);
			el.setAttribute(DocumentConstants.ATTRIBUTE_NAME, subCategoryName);
			categoryNode.appendChild(el);
			return el;
		}
		throw new BuildException(BuilderMessages.SUBCATEGORY_EMPTY);

	}

	/**
	 * Create or find category node
	 * 
	 * @param categoryName
	 * @return category node
	 */
	private Node findOrCreateCategoryNode(Document document, String categoryName) {
		if (categoryName != null && categoryName.trim().length() > 0) {
			Element rootElement = document.getDocumentElement();
			NodeList list = rootElement
					.getElementsByTagName(DocumentConstants.TAG_CATEGORY);
			for (int i = 0; i < list.getLength(); i++) {
				Node cNode = list.item(i);
				if (categoryName.equalsIgnoreCase(cNode.getAttributes()
						.getNamedItem(DocumentConstants.ATTRIBUTE_NAME)
						.getNodeValue())) {
					return cNode;
				}
			}

			Element el = document.createElement(DocumentConstants.TAG_CATEGORY);
			el.setAttribute(DocumentConstants.ATTRIBUTE_NAME, categoryName);
			rootElement.appendChild(el);
			return el;
		}
		throw new BuildException(BuilderMessages.CATEGORY_EMPTY);
	}

}
