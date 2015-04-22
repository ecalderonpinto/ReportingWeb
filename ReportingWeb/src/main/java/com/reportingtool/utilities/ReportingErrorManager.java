package com.reportingtool.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.dao.common.ErrorDAO;
import com.entities.dao.loader.LoadErrorDAO;
import com.entities.dao.loader.LoadFileDAO;
import com.entities.dao.reportingtool.ReportDataDAO;
import com.entities.dao.reportingtool.ReportDataErrorDAO;
import com.entities.dao.reportingtool.ReportErrorDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.entity.common.Error;
import com.entities.entity.loader.LoadError;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.reportingtool.ReportData;
import com.entities.entity.reportingtool.ReportDataError;
import com.entities.entity.reportingtool.ReportError;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.utilities.hibernate.VersionAuditor;

/**
 * Class to insert/disable errors from reports and loads
 * 
 * @author alberto.olivan
 *
 */
public class ReportingErrorManager {

	/**
	 * Function to create if not exists and reportDataError and add it to
	 * reportData
	 * 
	 * @param applicationContext
	 * @param typeError
	 * @param reportData
	 * @param reportDataErrorType
	 * @param reportDataErrorText
	 */
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
			reportData.getReportDataErrors().add(reportDataError);
		}
	}

	/**
	 * Function to disable a reportDataError from a reportData
	 * 
	 * @param applicationContext
	 * @param typeError
	 * @param reportData
	 * @param reportErrorType
	 */
	public static void disableReportDataError(
			ApplicationContext applicationContext, String typeError,
			ReportData reportData, String reportErrorType) {

		ReportDataErrorDAO reportDataErrorDAO = (ReportDataErrorDAO) applicationContext
				.getBean("reportDataErrorDAO");

		ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
				.getBean("reportDataDAO");

		reportData = reportDataDAO.findById(reportData.getId());

		ReportDataError reportDataErrorExample = new ReportDataError();
		reportDataErrorExample.setReportData(reportData);
		List<ReportDataError> reportDataErrors = reportDataErrorDAO
				.findByExample(reportDataErrorExample);

		// for (ReportDataError reportDataError :
		// reportData.getReportDataErrors()) {
		for (ReportDataError reportDataError : reportDataErrors) {
			if (reportDataError.getReportDataErrorType()
					.equals(reportErrorType)
					&& reportDataError.getError().getErrorType()
							.equals(typeError)) {
				reportDataErrorDAO.disable(reportDataError);
				// reportDataErrorDAO.delete(reportDataError); -> cascade error
				System.out.println("DEBUG_" + typeError
						+ " disable reportDataError " + reportErrorType
						+ " in " + reportData.getReportDataText() + " - "
						+ reportData.getReportField().getReportFieldName());
			}
		}
	}

	/**
	 * Function to create if not exists and reportError and add it to
	 * reportExecution
	 * 
	 * @param applicationContext
	 * @param typeError
	 * @param reportExecution
	 * @param reportErrorType
	 * @param reportErrorText
	 */
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
			reportExecution.getReportErrors().add(reportError);
		}
	}

	/**
	 * Function to disable a reportError from a reportExecution
	 * 
	 * @param applicationContext
	 * @param typeError
	 * @param reportExecution
	 * @param reportErrorType
	 */
	public static void disableReportError(
			ApplicationContext applicationContext, String typeError,
			ReportExecution reportExecution, String reportErrorType) {

		ReportErrorDAO reportErrorDAO = (ReportErrorDAO) applicationContext
				.getBean("reportErrorDAO");

		ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) applicationContext
				.getBean("reportExecutionDAO");

		reportExecution = reportExecutionDAO.findById(reportExecution.getId());
		for (ReportError reportError : reportExecution.getReportErrors()) {
			if (reportError.getReportErrorType().equals(reportErrorType)
					&& reportError.getError().getErrorType().equals(typeError)) {
				reportErrorDAO.disable(reportError);
				// reportDataErrorDAO.delete(reportDataError); -> cascade error
				System.out.println("DEBUG_" + typeError
						+ " disable reportDataError " + reportErrorType
						+ " in " + reportError.getReportErrorText() + " - "
						+ reportError.getReportErrorType());
			}
		}
	}

	/**
	 * Function to create if not exists and loadError and add it to loadFile
	 * 
	 * @param applicationContext
	 * @param typeError
	 * @param loadFile
	 * @param loadErrorType
	 * @param loadErrorText
	 */
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
			// loadErrorDAO.create(loadError);
			loadFile.getLoadErrors().add(loadError);
		}
	}

	/**
	 * Function to disable a loadError from a loadFile
	 * 
	 * @param applicationContext
	 * @param typeError
	 * @param loadFile
	 * @param loadErrorType
	 */
	public static void disableLoadError(ApplicationContext applicationContext,
			String typeError, LoadFile loadFile, String loadErrorType) {

		LoadErrorDAO loadErrorDAO = (LoadErrorDAO) applicationContext
				.getBean("loadErrorDAO");

		LoadFileDAO loadFileDAO = (LoadFileDAO) applicationContext
				.getBean("loadFileDAO");

		loadFile = loadFileDAO.findById(loadFile.getId());
		for (LoadError loadError : loadFile.getLoadErrors()) {
			if (loadError.getLoadErrorType().equals(loadErrorType)
					&& loadError.getError().getErrorType().equals(typeError)) {
				loadErrorDAO.disable(loadError);
				// reportDataErrorDAO.delete(reportDataError); -> cascade error
				System.out.println("DEBUG_" + typeError
						+ " disable reportDataError " + loadErrorType + " in "
						+ loadError.getLoadErrorText() + " - "
						+ loadError.getLoadErrorType());
			}
		}
	}

	/**
	 * Function to check if ReportExecution and ReportData has not deleted
	 * ReportErrors and ReportDataErrors
	 * 
	 * @param reportExecution
	 * @return reportExecution
	 */
	public static ReportExecution checkReportExecutionHasErrors(
			ReportExecution reportExecution) {

		boolean hasErrors = false;
		for (ReportData reportData : reportExecution.getReportDatas()) {
			hasErrors = false;
			List<ReportDataError> reportDataErrors = reportData
					.getReportDataErrors();
			System.out.println("reportDataErrors " + reportDataErrors
					+ " reportData " + reportData.getReportDataText() + " - "
					+ reportData.getReportField().getReportFieldName());
			for (ReportDataError reportDataError : reportDataErrors) {
				if (!reportDataError.getAuditor().isDeleted()) {
					hasErrors = true;
					break;
				}
			}
			reportData.setHasErrors(hasErrors);
		}

		hasErrors = false;
		List<ReportError> reportErrors = reportExecution.getReportErrors();
		for (ReportError reportError : reportErrors) {
			if (!reportError.getAuditor().isDeleted()) {
				hasErrors = true;
				break;
			}
		}
		reportExecution.setHasErrors(hasErrors);

		return reportExecution;
	}
}
