package com.reportingtool.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.dao.common.ErrorDAO;
import com.entities.dao.loader.LoadErrorDAO;
import com.entities.dao.reportingtool.ReportDataErrorDAO;
import com.entities.dao.reportingtool.ReportErrorDAO;
import com.entities.entity.common.Error;
import com.entities.entity.loader.LoadError;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportDataError;
import com.entities.entity.reportingtool.ReportError;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.utilities.hibernate.VersionAuditor;


public class ReportingErrorManager {


	public static void createReportDataError(ApplicationContext applicationContext, String typeError, ReportData reportData, String type,
			String text) {

		System.out.println("* Creating error: " + typeError + " " + type + " " + text);
		
		ErrorDAO errorDAO = (ErrorDAO) applicationContext.getBean("errorDAO");
		Error errorExample = new Error();
		errorExample.setErrorType(typeError);
		List<Error> errors = new ArrayList<Error>(
				errorDAO.findByExample(errorExample));
		Error error = new Error();
		error = errors.get(0);

		ReportDataError reportDataError = new ReportDataError(reportData,
				error, type, text, new VersionAuditor("error"));

		System.out.println("DEBUG_" + typeError + " : error final: "
				+ reportDataError.getReportDataErrorText()
				+ reportDataError.getReportDataErrorType()
				+ reportDataError.getReportData());

		ReportDataErrorDAO reportDataErrorDAO = (ReportDataErrorDAO) applicationContext
				.getBean("reportDataErrorDAO");

		reportDataErrorDAO.create(reportDataError);

	}
	
	
	public static void createReportError(ApplicationContext applicationContext, String typeError, ReportExecution reportExecution, String type,
			String text) {
		
		System.out.println("* Creating error: " + typeError + " " + type + " " + text);

		ErrorDAO errorDAO = (ErrorDAO) applicationContext.getBean("errorDAO");
		Error errorExample = new Error();
		errorExample.setErrorType(typeError);
		List<Error> errors = new ArrayList<Error>(
				errorDAO.findByExample(errorExample));
		Error error = new Error();
		error = errors.get(0);

		ReportError reportError = new ReportError(error, reportExecution,
				 type, text, new VersionAuditor("error"));

		System.out.println("DEBUG_" + typeError + " : error final: "
				+ reportError.getReportErrorText()
				+ reportError.getReportErrorType()
				+ reportError.getReportExecution().getReportPeriodYear()
				+ reportError.getReportExecution().getReportPeriodType());

		ReportErrorDAO reportErrorDAO = (ReportErrorDAO) applicationContext
				.getBean("reportErrorDAO");

		reportErrorDAO.create(reportError);

	}
	
	
	public static void createLoadError(ApplicationContext applicationContext, String typeError, LoadFile loadFile, String type,
			String text) {
		
		System.out.println("* Creating error: " + typeError + " " + type + " " + text);

		ErrorDAO errorDAO = (ErrorDAO) applicationContext.getBean("errorDAO");
		Error errorExample = new Error();
		errorExample.setErrorType(typeError);
		List<Error> errors = new ArrayList<Error>(
				errorDAO.findByExample(errorExample));
		Error error = new Error();
		error = errors.get(0);

		LoadError loadError = new LoadError(error, loadFile,
				 type, text, new VersionAuditor("error"));

		System.out.println("DEBUG_" + typeError + " : error final: "
				+ loadError.getLoadErrorText()
				+ loadError.getLoadErrorType()
				+ loadError.getLoadFile().getFileConfig().getFileConfigName()
				+ loadError.getLoadFile().getLoadFileName());

		LoadErrorDAO loadErrorDAO = (LoadErrorDAO) applicationContext
				.getBean("loadErrorDAO");

		loadErrorDAO.create(loadError);

	}


}
