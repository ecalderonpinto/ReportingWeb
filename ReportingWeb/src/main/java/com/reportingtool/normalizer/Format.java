package com.reportingtool.normalizer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;

import com.entities.dao.loader.LoadRawDataDAO;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.LoadRawData;
import com.reportingtool.utilities.ReportingErrorManager;

/**
 * Class to format date and numbers to same patter
 * 
 * @author alberto.olivan
 *
 */
public class Format {

	private ApplicationContext applicationContext;

	public static final String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
	public static final String datePattern = "yyyy-MM-dd";

	/**
	 * Constructor of Format with an applicationContext
	 * 
	 * @param applicationContext
	 */
	public Format(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Main function to format loadRawData, can create loadErrors
	 * 
	 * @param loadRawData
	 * @return loadRawData formated
	 */
	public LoadRawData formatRaw(LoadRawData loadRawData) {

		System.out.println("DEBUG_" + "Format: "
				+ loadRawData.getLoadRawDataType());

		if (loadRawData.getLoadRawDataType().equals("DATE")) {
			this.dateFormat(loadRawData);
		}

		if (loadRawData.getLoadRawDataType().equals("DATETIME")) {
			this.dateTimeFormat(loadRawData);
		}

		if (loadRawData.getLoadRawDataType().equals("NUMBER")) {
			this.numberFormat(loadRawData);
		}

		return loadRawData;
	}

	/**
	 * Function to format a type Date with datePattern = "yyyy-MM-dd";
	 * 
	 * @param loadRawData
	 * @return loadRawData Date formated
	 */
	public LoadRawData dateFormat(LoadRawData loadRawData) {
		String dateText = loadRawData.getLoadRawDataText();

		// find config of column
		FileColum fileColum = loadRawData.getFileColum();

		// only accept format yyyy-MM-dd
		String dateFormat = fileColum.getColumFormat();

		System.out.println("DEBUG_" + "FormatDate "
				+ loadRawData.getLoadRawDataText() + " format "
				+ fileColum.getColumFormat());

		String newstring = "";
		try {
			// SimpleDateFormat#parse() to parse a String in a certain pattern
			// into a Date
			Date date = new SimpleDateFormat(dateFormat).parse(dateText);
			// SimpleDateFormat#format() to format a Date into a String in a
			// certain pattern
			newstring = new SimpleDateFormat(datePattern).format(date);

		} catch (Exception e) {
			e.printStackTrace();
			// create loadError
			ReportingErrorManager.createLoadError(applicationContext,
					"NORMALIZER", loadRawData.getLoadRaw().getLoadFile(),
					"FORMAT",
					"INVALID DATE FORMAT: " + loadRawData.getLoadRawDataText()
							+ " " + fileColum.getColumFormat());
		}

		System.out.println("DEBUG_" + "FormatDate Orig: " + dateText + " New "
				+ newstring);

		// set final date
		loadRawData.setLoadRawDataText(newstring);
		
		LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) applicationContext
				.getBean("loadRawDataDAO");
		loadRawDataDAO.edit(loadRawData);

		return loadRawData;
	}

	/**
	 * Function to format a type DateTime with dateTimePattern =
	 * "yyyy-MM-ddTHH:mm:ss";
	 * 
	 * @param loadRawData
	 * @return loadRawData DateTime formated
	 */
	public LoadRawData dateTimeFormat(LoadRawData loadRawData) {
		String dateText = loadRawData.getLoadRawDataText();

		// find config of column
		FileColum fileColum = loadRawData.getFileColum();

		// only accept format yyyy-MM-ddTHH:mm:ss
		String dateFormat = fileColum.getColumFormat();

		String newstring = "";
		try {
			// SimpleDateFormat#parse() to parse a String in a certain pattern
			// into a Date
			Date date = new SimpleDateFormat(dateFormat).parse(dateText);
			// SimpleDateFormat#format() to format a Date into a String in a
			// certain pattern
			newstring = new SimpleDateFormat(dateTimePattern).format(date);
		} catch (Exception e) {
			e.printStackTrace();
			// create loadError
			ReportingErrorManager.createLoadError(
					applicationContext,
					"NORMALIZER",
					loadRawData.getLoadRaw().getLoadFile(),
					"FORMAT",
					"INVALID DATETIME FORMAT: "
							+ loadRawData.getLoadRawDataText() + " "
							+ fileColum.getColumFormat());
		}

		System.out.println("DEBUG_" + "FormatDateTime Orig: " + dateText
				+ " New" + newstring);

		// set final date
		loadRawData.setLoadRawDataText(newstring);

		LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) applicationContext
				.getBean("loadRawDataDAO");
		loadRawDataDAO.edit(loadRawData);

		return loadRawData;
	}

	/**
	 * Function to format a number with patter: #.#
	 * 
	 * @param loadRawData
	 * @return loadRawData number formated
	 */
	public LoadRawData numberFormat(LoadRawData loadRawData) {
		String number = loadRawData.getLoadRawDataText();

		// find config of column
		FileColum fileColum = loadRawData.getFileColum();

		// input format -> example => output result
		// ###,###.### -> 12,100.01 => 12100.01
		// ###.###,### -> 1.000,5 => 1000.5
		// 000000.000 -> 000124.010 => 124.01
		// $###.### -> $500 => 500
		// ##% -> 25% => 25
		// http://docs.oracle.com/javase/tutorial/java/data/numberformat.html

		// only acept format #.# removing spaces, characters and ',' '.' of
		// thousands
		String numberFormat = fileColum.getColumFormat();

		int dot = numberFormat.indexOf(".");
		int comma = numberFormat.indexOf(",");
		if (dot > comma) {
			// dot before comma -> #.# || ###,###.## => remove ','
			number.replace(",", "");
		} else {
			// dot after comma -> #,# || ###.###,## => remove '.' change ',' ->
			// '.'
			number.replace(".", "");
			number.replace(",", ".");
		}

		// clean all not digit characters except '.'
		// http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
		number.replaceAll("[\\D+&&[^\\.]]", "");

		System.out.println("DEBUG_" + "FormatNumber Orig: "
				+ loadRawData.getLoadRawDataText() + " New" + number);

		// TODO:RT check if it is ok
		
		// set the final number
		loadRawData.setLoadRawDataText(number);

		LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) applicationContext
				.getBean("loadRawDataDAO");
		loadRawDataDAO.edit(loadRawData);

		return loadRawData;
	}
}
