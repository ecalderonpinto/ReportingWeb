package com.reportingtool.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportSemantic;

/**
 * Class to check semantic rules of reportExecution
 * 
 * @author alberto.olivan
 *
 */
public class Semantic {

	private ApplicationContext applicationContext;

	/**
	 * Constructor of Semantic with an applicationContext
	 * 
	 * @param applicationContext
	 */
	public Semantic(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Main function to check semantic rules of a reportExecution
	 * 
	 * @param reportExecution
	 */
	public void checkSemantic(ReportExecution reportExecution) {
//		List<ReportSemantic> reportSemantics = new ArrayList<ReportSemantic>(
//				reportExecution.getReportCatalog().getReportSemantics());
//
//		for (ReportSemantic reportSemantic : reportSemantics) {
//			String rule = reportSemantic.getReportingSemanticRule();
//			
//			// TODO:RT make semantic validation
//		}
	}

}
