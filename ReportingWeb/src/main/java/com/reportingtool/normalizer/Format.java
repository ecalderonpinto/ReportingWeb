package com.reportingtool.normalizer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;

import com.entities.dao.loader.LoadRawDataDAO;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.LoadRawData;
import com.reportingtool.utilities.ReportingErrorManager;

public class Format {

	public static final String dateTimePattern = "yyyy-MM-ddTHH:mm:ss";
	public static final String datePattern = "yyyy-MM-dd";

	private ApplicationContext applicationContext;

	public Format(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	// main function to format loadRawData
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

		return loadRawData;
	}

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

		// set the final number
		loadRawData.setLoadRawDataText(number);

		LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) applicationContext
				.getBean("loadRawDataDAO");
		loadRawDataDAO.edit(loadRawData);

		return loadRawData;
	}
}
