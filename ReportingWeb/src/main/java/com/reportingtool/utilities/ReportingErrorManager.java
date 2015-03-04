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

	public static void createReportDataError(
			ApplicationContext applicationContext, String typeError,
			ReportData reportData, String reportDataErrorType,
			String reportDataErrorText) {

		System.out.println("* Creating error: " + typeError + " "
				+ reportDataErrorType + " " + reportDataErrorText);

		ErrorDAO errorDAO = (ErrorDAO) applicationContext.getBean("errorDAO");
		Error errorExample = new Error();
		errorExample.setErrorType(typeError);
		List<Error> errors = new ArrayList<Error>(
				errorDAO.findByExample(errorExample));
		Error error = new Error();
		error = errors.get(0);

		// ReportDataError reportDataError = new ReportDataError(reportData,
		// error, type, text, new VersionAuditor("error"));

		ReportDataError reportDataError = new ReportDataError(error,
				reportData, reportDataErrorType, reportDataErrorText);

		System.out.println("DEBUG_" + typeError + " : error final: "
				+ reportDataError.getReportDataErrorText()
				+ reportDataError.getReportDataErrorType()
				+ reportDataError.getReportData());

		ReportDataErrorDAO reportDataErrorDAO = (ReportDataErrorDAO) applicationContext
				.getBean("reportDataErrorDAO");

		// search if this error already exists in database
		List<ReportDataError> reportDataErrorList = reportDataErrorDAO
				.findByExample(reportDataError);
		boolean iguales = false;
		for (ReportDataError reportDataErrorExample : reportDataErrorList) {
			if (reportDataErrorExample.equals(reportDataError)) {
				System.out.println("DEBUG_" + typeError + " error ya existe.");
				iguales = true;
				break;
			}
		}
		if (!iguales) {
			reportDataError.setAuditor(new VersionAuditor("error"));
			reportDataErrorDAO.create(reportDataError);
		}
	}

	public static void createReportError(ApplicationContext applicationContext,
			String typeError, ReportExecution reportExecution,
			String reportErrorType, String reportErrorText) {

		System.out.println("* Creating error: " + typeError + " "
				+ reportErrorType + " " + reportErrorText);

		ErrorDAO errorDAO = (ErrorDAO) applicationContext.getBean("errorDAO");
		Error errorExample = new Error();
		errorExample.setErrorType(typeError);
		List<Error> errors = new ArrayList<Error>(
				errorDAO.findByExample(errorExample));
		Error error = new Error();
		error = errors.get(0);

		ReportError reportError = new ReportError(error, reportExecution,
				reportErrorType, reportErrorText);

		System.out.println("DEBUG_" + typeError + " : error final: "
				+ reportError.getReportErrorText()
				+ reportError.getReportErrorType()
				+ reportError.getReportExecution().getReportPeriodYear()
				+ reportError.getReportExecution().getReportPeriodType());

		ReportErrorDAO reportErrorDAO = (ReportErrorDAO) applicationContext
				.getBean("reportErrorDAO");

		// search if this error already exists in database
		List<ReportError> reportErrorList = reportErrorDAO
				.findByExample(reportError);
		boolean iguales = false;
		for (ReportError reportErrorExample : reportErrorList) {
			if (reportErrorExample.equals(reportError)) {
				System.out.println("DEBUG_" + typeError + " error ya existe.");
				iguales = true;
				break;
			}
		}
		if (!iguales) {
			reportError.setAuditor(new VersionAuditor("error"));
			reportErrorDAO.create(reportError);
		}
	}

	public static void createLoadError(ApplicationContext applicationContext,
			String typeError, LoadFile loadFile, String loadErrorType,
			String loadErrorText) {

		System.out.println("* Creating error: " + typeError + " "
				+ loadErrorType + " " + loadErrorText);

		ErrorDAO errorDAO = (ErrorDAO) applicationContext.getBean("errorDAO");
		Error errorExample = new Error();
		errorExample.setErrorType(typeError);
		List<Error> errors = new ArrayList<Error>(
				errorDAO.findByExample(errorExample));
		Error error = new Error();
		error = errors.get(0);

		LoadError loadError = new LoadError(error, loadFile, loadErrorType,
				loadErrorText);

		System.out.println("DEBUG_" + typeError + " : error final: "
				+ loadError.getLoadErrorText() + loadError.getLoadErrorType()
				+ loadError.getLoadFile().getFileConfig().getFileConfigName()
				+ loadError.getLoadFile().getLoadFileName());

		LoadErrorDAO loadErrorDAO = (LoadErrorDAO) applicationContext
				.getBean("loadErrorDAO");

		// search if this error already exists in database
		List<LoadError> loadErrorList = loadErrorDAO.findByExample(loadError);
		boolean iguales = false;
		for (LoadError loadErrorExample : loadErrorList) {
			if (loadErrorExample.equals(loadError)) {
				System.out.println("DEBUG_" + typeError + " error ya existe.");
				iguales = true;
				break;
			}
		}
		if (!iguales) {
			loadError.setAuditor(new VersionAuditor("error"));
			loadErrorDAO.create(loadError);
		}
	}
}
