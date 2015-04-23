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

		System.out.println("DEBUG_" + typeError + " Creating reportDataError ("
				+ reportData.getReportField().getReportFieldNum() + ") "
				+ typeError + " " + reportDataErrorType + " "
				+ reportDataErrorText + " - " + reportData.getReportDataText());

		Error error = findError(applicationContext, typeError);

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
				System.out.println("DEBUG_" + typeError
						+ " enable reportDataError ("
						+ reportData.getReportField().getReportFieldNum()
						+ ") "
						+ reportDataErrorExample.getReportDataErrorType()
						+ " in " + reportData.getReportDataText() + " - "
						+ reportData.getReportField().getReportFieldName());
				reportDataErrorDAO.enable(reportDataErrorExample);
				break;
			}
		}

		// create new error
		if (!iguales) {
			reportDataError.setVersionAuditor(new VersionAuditor("error"));
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

		// ReportDataErrorDAO reportDataErrorDAO = (ReportDataErrorDAO)
		// applicationContext
		// .getBean("reportDataErrorDAO");
		//
		// ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
		// .getBean("reportDataDAO");
		//
		// reportData = reportDataDAO.findById(reportData.getId());
		//
		// Error error = findError(applicationContext, typeError);
		//
		// ReportDataError reportDataErrorExample = new ReportDataError();
		// reportDataErrorExample.setReportData(reportData);
		// reportDataErrorExample.setError(error);
		// reportDataErrorExample.setReportDataErrorType(reportErrorType);
		//
		// System.out.println("LOL " +
		// reportDataErrorExample.getReportData().getId()
		// + " " +reportDataErrorExample.getReportDataErrorType());
		//
		// List<ReportDataError> reportDataErrors = reportDataErrorDAO
		// .findByExample(reportDataErrorExample);
		//
		// System.out.println("reportDataErrors size ! " +
		// reportDataErrors.size());
		//
		// for (ReportDataError reportDataError : reportDataErrors) {
		// if (reportDataErrorExample.equals(reportDataError)) {
		// System.out.println("vaya no me lo experaba ");
		// }
		// reportDataErrorDAO.disable(reportDataError);
		// // reportDataErrorDAO.delete(reportDataError); -> cascade error
		// System.out.println("DEBUG_" + typeError
		// + " disable reportDataError ("
		// + reportData.getReportField().getReportFieldNum() + ") "
		// + reportErrorType + " in " + reportData.getReportDataText()
		// + " - " + reportData.getReportField().getReportFieldName());
		// }

		ReportDataErrorDAO reportErrorDAO = (ReportDataErrorDAO) applicationContext
				.getBean("reportDataErrorDAO");

		ReportDataDAO reportDataDAO = (ReportDataDAO) applicationContext
				.getBean("reportDataDAO");

		reportData = reportDataDAO.findById(reportData.getId());

		for (ReportDataError reportDataError : reportData.getReportDataErrors()) {
			if (reportDataError.getReportDataErrorType()
					.equals(reportErrorType)
					&& reportDataError.getError().getErrorType()
							.equals(typeError)) {
				reportErrorDAO.disable(reportDataError);
				// reportDataErrorDAO.delete(reportDataError); -> cascade
				// error
				System.out.println("DEBUG_" + typeError
						+ " disable reportError " + reportErrorType + " in "
						+ reportDataError.getReportDataErrorText() + " - "
						+ reportDataError.getReportDataErrorType());
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

		System.out.println("DEBUG_" + typeError + " Creating reportError: ("
				+ reportExecution.getReportExecutionName() + ") " + typeError
				+ " " + reportErrorType + " " + reportErrorText);

		Error error = findError(applicationContext, typeError);

		ReportError reportError = new ReportError(error, reportExecution,
				reportErrorType, reportErrorText);

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
				System.out.println("DEBUG_" + typeError
						+ " enabled reportError "
						+ reportErrorExample.getReportErrorType() + " in "
						+ reportExecution.getReportExecutionName());
				reportErrorDAO.enable(reportErrorExample);
				break;
			}
		}
		if (!iguales) {
			reportError.setVersionAuditor(new VersionAuditor("error"));
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

		// ReportErrorDAO reportErrorDAO = (ReportErrorDAO) applicationContext
		// .getBean("reportErrorDAO");
		//
		// ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO)
		// applicationContext
		// .getBean("reportExecutionDAO");
		//
		// reportExecution =
		// reportExecutionDAO.findById(reportExecution.getId());
		//
		// Error error = findError(applicationContext, typeError);
		//
		// ReportError reportErrorExample = new ReportError();
		// reportErrorExample.setReportExecution(reportExecution);
		// reportErrorExample.setError(error);
		// reportErrorExample.setReportErrorType(reportErrorType);
		//
		// List<ReportError> reportErrorExamples = reportErrorDAO
		// .findByExample(reportErrorExample);
		//
		// for (ReportError reportError : reportErrorExamples) {
		// reportErrorDAO.disable(reportError);
		// // reportDataErrorDAO.delete(reportDataError); -> cascade error
		// System.out.println("DEBUG_" + typeError + " disable reportError "
		// + reportErrorType + " in "
		// + reportExecution.getReportExecutionName() + " - "
		// + reportError.getReportErrorText());
		// }

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
						+ " disable reportError " + reportErrorType + " in "
						+ reportError.getReportErrorText() + " - "
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

		System.out.println("* Creating loadError: " + typeError + " "
				+ loadErrorType + " " + loadErrorText);

		Error error = findError(applicationContext, typeError);

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
				System.out.println("DEBUG_" + typeError + " enabled loadError "
						+ loadErrorExample.getLoadErrorType() + " in "
						+ loadFile.getLoadFileName());
				loadErrorDAO.enable(loadErrorExample);
				break;
			}
		}
		if (!iguales) {
			loadError.setVersionAuditor(new VersionAuditor("error"));
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

		// LoadErrorDAO loadErrorDAO = (LoadErrorDAO) applicationContext
		// .getBean("loadErrorDAO");
		//
		// LoadFileDAO loadFileDAO = (LoadFileDAO) applicationContext
		// .getBean("loadFileDAO");
		//
		// loadFile = loadFileDAO.findById(loadFile.getId());
		//
		// Error error = findError(applicationContext, typeError);
		//
		// LoadError loadErrorExample = new LoadError();
		// loadErrorExample.setLoadFile(loadFile);
		// loadErrorExample.setError(error);
		// loadErrorExample.setLoadErrorType(loadErrorType);
		//
		// List<LoadError> loadErrorExamples = loadErrorDAO
		// .findByExample(loadErrorExample);
		//
		// for (LoadError loadError : loadErrorExamples) {
		// loadErrorDAO.disable(loadError);
		// // reportDataErrorDAO.delete(reportDataError); -> cascade error
		// System.out.println("DEBUG_" + typeError + " disable reportError "
		// + loadErrorType + " in " + loadFile.getLoadFileName()
		// + " - " + loadError.getLoadErrorText());
		// }

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
				System.out.println("DEBUG_" + typeError + " disable loadError "
						+ loadErrorType + " in " + loadError.getLoadErrorText()
						+ " - " + loadError.getLoadErrorType());
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
		boolean dataHasErrors = false;

		for (ReportData reportData : reportExecution.getReportDatas()) {
			hasErrors = false;
			List<ReportDataError> reportDataErrors = reportData
					.getReportDataErrors();
			// System.out.println("reportDataErrors " + reportDataErrors
			// + " reportData " + reportData.getReportDataText() + " - "
			// + reportData.getReportField().getReportFieldName());
			for (ReportDataError reportDataError : reportDataErrors) {
				if (!reportDataError.getVersionAuditor().isDeleted()) {
					hasErrors = true;
					dataHasErrors = true;
					break;
				}
			}
			reportData.setHasErrors(hasErrors);
		}

		// if any reportData has error, reportExecution has it
		if (!dataHasErrors) {
			hasErrors = false;
			List<ReportError> reportErrors = reportExecution.getReportErrors();
			for (ReportError reportError : reportErrors) {
				if (!reportError.getVersionAuditor().isDeleted()) {
					hasErrors = true;
					break;
				}
			}
			reportExecution.setHasErrors(hasErrors);
		} else {
			reportExecution.setHasErrors(dataHasErrors);
		}

		return reportExecution;
	}

	private static Error findError(ApplicationContext applicationContext,
			String typeError) {
		Error error = new Error();
		try {
			ErrorDAO errorDAO = (ErrorDAO) applicationContext
					.getBean("errorDAO");
			error.setErrorType(typeError);
			List<Error> errors = new ArrayList<Error>(
					errorDAO.findByExample(error));

			error = errors.get(0);
		} catch (Exception e) {
			System.out.println("ERROR in ReportingErrorManager, typeError "
					+ typeError + " does not exist.");
		}
		return error;
	}
}
