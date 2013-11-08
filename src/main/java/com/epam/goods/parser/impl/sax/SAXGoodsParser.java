package com.epam.goods.parser.impl.sax;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.epam.goods.exception.ParseException;
import com.epam.goods.model.Category;
import com.epam.goods.parser.iface.GoodsParser;

public class SAXGoodsParser implements GoodsParser {

	private String filename;

	public SAXGoodsParser(String filename) {
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

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			
			parser.parse(in, handler);
			return handler.getCategories();
			
		} catch (IOException | SAXException | ParserConfigurationException e) {
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
