package com.epam.goods.builder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.goods.exception.BuildException;
import com.epam.goods.model.Color;
import com.epam.goods.model.Good;

/**
 * Builds good object
 * 
 * @author azimkhan
 */
public class GoodBuilder {
	/**
	 * store good data
	 */
	private Good good;

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static final Pattern pattern = Pattern.compile("[A-Z]{2}[0-9]{3}");

	/**
	 * Create good
	 * 
	 * @param name
	 */
	public void createGood(String name) {
		if (name != null && name.length() > 0) {
			good = new Good(name);
			return;
		}

		throw new BuildException(BuilderMessages.NAME_EMPTY);
	}

	/**
	 * Build producer
	 * 
	 * @param producer
	 *            value
	 */
	public void buildProducer(String producer) {
		if (producer != null && producer.length() > 0) {
			good.setProducer(producer);
			return;
		}
		throw new BuildException(BuilderMessages.PRODUCER_EMPTY);
	}

	/**
	 * Build model
	 * 
	 * @param model
	 *            value
	 */
	public void buildModel(String model) {
		if (model != null && model.length() > 0) {
			Matcher m = pattern.matcher(model);
			if (m.matches()) {
				good.setModel(model);
				return;
			} else {
				throw new BuildException(BuilderMessages.MODEL_PATTERN);
			}
		}
		throw new BuildException(BuilderMessages.MODEL_EMPTY);
	}

	/**
	 * Build date
	 * 
	 * @param date
	 *            value
	 */
	public void buildDate(String date) {
		if (date != null && date.length() > 0) {
			Calendar c = GregorianCalendar.getInstance();
			try {
				Date d = dateFormat.parse(date);
				c.setTime(d);
				good.setDate(c);
				return;
			} catch (ParseException e) {
				throw new BuildException(BuilderMessages.DATE_FORMAT);
			}
		}

		throw new BuildException(BuilderMessages.DATE_EMPTY);
	}

	/**
	 * Build color
	 * 
	 * @param color
	 *            value
	 */
	public void buildColor(String color) {

		if (color != null && color.length() > 0) {
			try {
				good.setColor(Color.valueOf(color.toUpperCase()));
				return;
			} catch (IllegalArgumentException e) {
				throw new BuildException(BuilderMessages.COLOR_INVALID);
			}
		}

		throw new BuildException(BuilderMessages.COLOR_EMPTY);
	}

	/**
	 * Build price
	 * 
	 * @param price
	 *            value
	 */
	public void buildPrice(String price) {
		if (price != null && price.length() > 0) {
			try {
				int p = Integer.parseInt(price);
				if (p > 0) {
					good.setPrice(p);
					return;
				}

				throw new BuildException(BuilderMessages.PRICE_INVALID);
			} catch (NumberFormatException e) {
				throw new BuildException(BuilderMessages.PRICE_INVALID);
			}
		}

		throw new BuildException(BuilderMessages.PRICE_EMPTY);
	}

	/**
	 * Build not in stock
	 * 
	 * @param notInStock
	 *            value
	 */
	public void buildNotInStock(String notInStock) {
		if ("true".equalsIgnoreCase(notInStock)) {
			good.setNotInStock(true);
		} else {
			good.setNotInStock(false);
		}
	}

	/**
	 * Get good
	 * 
	 * @return current good
	 */
	public Good getGood() {
		return good;
	}
}
