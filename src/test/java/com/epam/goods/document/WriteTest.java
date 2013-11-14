package com.epam.goods.document;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.goods.model.Color;
import com.epam.goods.model.Good;
public class WriteTest {
	
	static class Writer extends Thread{
		private String goodName;
		
		public Writer(String goodName) {
			super();
			this.goodName = goodName;
		}

		@Override
		public void run() {
			Good good = new Good(goodName);
			good.setColor(Color.WHITE);
			good.setDate(Calendar.getInstance());
			good.setModel("AA001");
			good.setPrice(100);
			good.setProducer("test");
			try {
				writer.write(categoryName, subcategoryName, good);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	private static String filename = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/goods-test.xml";
	
	private static DocumentWriter writer;
	private static String categoryName = "test";
	private static String subcategoryName = "test";
	
	@BeforeClass
	public static void setUp(){
		writer = new DocumentWriter(filename);
		
		
	}
	
	@Test
	public void writeTest() throws Exception{
		Random r = new Random(System.currentTimeMillis());
		List<String> names = new LinkedList<String>();
		
		for(int i = 1; i < 100; i++){
			names.add("writer" + r.nextInt());
		}
		
		for (String name : names){
			new Writer(name).run();
		}
		
		Assert.assertTrue(goodsExist(names));
	}
	
	private boolean goodsExist(List<String> goodNames) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document document = builder.parse(new File(filename));
		
		Element root = document.getDocumentElement();
		NodeList cats = root.getElementsByTagName("category");
		for (int i = 0; i < cats.getLength(); i++) {
			Node cNode = cats.item(i);
			if (cNode.getAttributes().getNamedItem("name").getNodeValue().equalsIgnoreCase(categoryName)){
				NodeList subcats = cNode.getChildNodes();
				for (int j = 0; j < subcats.getLength(); j++) {
					Node subcat = subcats.item(j);
					if (subcat.getNodeType() == Node.ELEMENT_NODE && subcat.getAttributes().getNamedItem("name").getNodeValue().equalsIgnoreCase(subcategoryName)){
					NodeList goods = subcat.getChildNodes();
						for (int k = 0; k < goods.getLength(); k++) {
							Node good = goods.item(k);
							if (good.getNodeType() == Node.ELEMENT_NODE){
								String name = good.getAttributes().getNamedItem("name").getNodeValue();
								if (goodNames.contains(name)){
									goodNames.remove(name);
								}
							}
						}
					
				}
			}
		}
		}
		return goodNames.isEmpty();
		
	}
	
}
