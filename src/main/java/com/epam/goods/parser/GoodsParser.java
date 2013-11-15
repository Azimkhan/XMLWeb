package com.epam.goods.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.goods.exception.ParseException;
import com.epam.goods.model.Category;


public abstract class GoodsParser {
	private static final ReentrantLock lock = new ReentrantLock();
	private static final Logger logger = LoggerFactory.getLogger(GoodsParser.class);
	
	public List<Category> parse(String filename){
		lock.lock();
		FileInputStream in = null;
		try{
			File file = new File(filename);
			in = new FileInputStream(file);
			return parse(in);
		} catch (FileNotFoundException e) {
			throw new ParseException(e);
		} finally {
			if (in != null){
				try {
					in.close();
				} catch (IOException e) {
					logger.error("input stream close error: {}", e);
				}
			}
			
			lock.unlock();
			
		}
		
	}
	
	protected abstract List<Category> parse(FileInputStream in) throws ParseException;
}
