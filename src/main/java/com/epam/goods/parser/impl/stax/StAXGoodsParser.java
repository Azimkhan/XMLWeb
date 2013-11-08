package com.epam.goods.parser.impl.stax;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import com.epam.goods.exception.ParseException;
import com.epam.goods.model.Category;
import com.epam.goods.parser.iface.GoodsParser;

public class StAXGoodsParser implements GoodsParser {
	private String filename;

	/**
	 * @param filename
	 */
	public StAXGoodsParser(String filename) {
		super();
		this.filename = filename;
	}

	@Override
	public List<Category> parse() {
		FileLock lock = null;
		FileInputStream in = null;
		RandomAccessFile raf = null;
		XMLStreamReader reader = null;
		try {
			// open random access file
			raf = new RandomAccessFile(filename, "rw");
			in = new FileInputStream(raf.getFD());
			
			// lock file
			lock = raf.getChannel().lock();

			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			reader = inputFactory.createXMLStreamReader(in);
			StAXHandler handler = new StAXHandler(reader);
			
			// parse
			return handler.handle();

		} catch (IOException | XMLStreamException e) {
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

			if (reader != null) {
				try {
					reader.close();
				} catch (XMLStreamException e) {
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
