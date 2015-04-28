package com.reportingtool.validator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.springframework.context.ApplicationContext;

import bsh.Interpreter;

import com.entities.dao.reportingtool.ReportSemanticDAO;
import com.entities.dictionary.ErrorTypeEnum;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportSemantic;
import com.reportingtool.utilities.ReportingErrorManager;

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

		System.out.println("Start Semantic analisys");

		ReportSemanticDAO reportSemanticDAO = (ReportSemanticDAO) applicationContext
				.getBean("reportSemanticDAO");

		ReportSemantic reportSemanticExample = new ReportSemantic();
		reportSemanticExample.setReportCatalog(reportExecution
				.getReportCatalog());

		List<ReportSemantic> reportSemantics = reportSemanticDAO
				.findByExample(reportSemanticExample);

		for (ReportSemantic reportSemantic : reportSemantics) {
			// execute semantic rule and delete/create error
			executeRuleScript(reportSemantic, reportExecution);
		}
	}

	/**
	 * Function that execute a reportSemantic rule in reportExecution
	 * 
	 * @param reportSemantic
	 * @param reportExecution
	 * @return result of rule execution
	 */
	private String executeRuleScript(ReportSemantic reportSemantic,
			ReportExecution reportExecution) {

		String result = null;
		PrintStream stream = null;

		try {

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			stream = new PrintStream(outputStream, false, "ISO-8859-1");

			StringBuffer initialScript = null;
			initialScript = new StringBuffer();
			initialScript.append("\nimport java.util.*;\n");
			initialScript.append("import java.text.*;\n");
			initialScript
					.append("import org.springframework.context.ApplicationContext;\n");
			initialScript
					.append("import com.entities.entity.reportingtool.ReportExecution;\n");
			initialScript
					.append("import com.entities.entity.reportingtool.ReportData;\n");
			initialScript
					.append("import com.entities.entity.reportingtool.ReportField;\n");
			initialScript
					.append("import com.reportingtool.utilities.ReportUtilities;\n");
			initialScript.append("import java.math.BigInteger;\n");
			initialScript.append("import java.util.List;\n");

			initialScript.append(reportSemantic.getReportingSemanticRule());

			Interpreter beanshellContext = null;
			beanshellContext = new Interpreter(null, stream, stream, false);

			System.out.println("DEBUG_" + "Semantic: "
					+ reportSemantic.getReportingSemanticRule());

			beanshellContext.set("reportExecution", reportExecution);
			beanshellContext.set("reportDatas",
					reportExecution.getReportDatas());
			beanshellContext.set("result", result);

			beanshellContext.eval(initialScript.toString());
			result = (String) beanshellContext.get("result");

			if (result == null) {
				// error
				System.out.println("DEBUG_" + "Semantic: ERROR ->"
						+ reportSemantic.getReportingSemanticName());

				ReportingErrorManager.createReportError(
						applicationContext,
						ErrorTypeEnum.SEMANTIC.getErrorType(),
						reportExecution,
						reportSemantic.getReportingSemanticName(),
						"Semantic Rule "
								+ reportSemantic.getReportingSemanticName()
								+ " unfulfilled. Suggestion: "
								+ reportSemantic.getReportingSemanticSugg());
			} else {
				// ok
				System.out.println("DEBUG_" + "Semantic: Ok, result " + result
						+ " ->" + reportSemantic.getReportingSemanticName());

				ReportingErrorManager.disableReportError(applicationContext,
						ErrorTypeEnum.SEMANTIC.getErrorType(), reportExecution,
						reportSemantic.getReportingSemanticName());
			}

		} catch (Exception e) {
			e.printStackTrace();
			ReportingErrorManager.createReportError(
					applicationContext,
					ErrorTypeEnum.SEMANTIC.getErrorType(),
					reportExecution,
					"FATAL",
					"Fatal error when processing "
							+ reportSemantic.getReportingSemanticName() + ": "
							+ e.getMessage());
		} finally {
			stream.flush();
			stream.close();
		}

		return result;
	}

}
