package com.epam.goods.parser.impl.dom;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.epam.goods.exception.ParseException;
import com.epam.goods.model.Category;
import com.epam.goods.parser.iface.GoodsParser;

public class DOMGoodsParser implements GoodsParser{
	private String filename;

	public DOMGoodsParser(String filename) {
		this.filename = filename;
	}

	public List<Category> parse() throws ParseException {

		FileLock lock = null;
		FileInputStream in = null;
		RandomAccessFile raf = null;
		
		try {
			// lock file
			raf  =  new RandomAccessFile(filename, "rw");
			in   =  new FileInputStream(raf.getFD());
			lock =  raf.getChannel().lock();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document document = builder.parse(in);
			DOMHandler handler = new DOMHandler(document);
			
			return handler.handle();
			
		} catch (IOException | ParserConfigurationException | SAXException e) {
			throw new ParseException(e);
			
		} finally {
			// unlock file
			if (lock != null) {
				try {
					lock.release();
				} catch (IOException e) {
					// TODO logs
				}
			}

			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO logs
				}
			}

			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					// TODO logs
				}
			}
		}
	}
}
