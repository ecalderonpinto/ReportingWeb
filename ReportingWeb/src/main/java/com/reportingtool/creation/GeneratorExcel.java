package com.reportingtool.creation;

import org.springframework.context.ApplicationContext;

import com.entities.entity.reportingtool.ReportExecution;

/**
 * Class to generate Excel files from reportExecution
 * 
 * @author alberto.olivan
 *
 */
public class GeneratorExcel {

	private ApplicationContext applicationContext;

	/**
	 * Constructor of GeneratorExcel with an applicationContext
	 * 
	 * @param applicationContext
	 */
	public GeneratorExcel(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Main function that generate a Excel report with a reportExecution
	 * 
	 * @param reportExecution
	 * @return excel format of reportExecution
	 */
	public String generateExcel(ReportExecution reportExecution) {

		String result = "";

		// TODO:RT function empty, need POI libraries from maven

		return result;
	}
}
