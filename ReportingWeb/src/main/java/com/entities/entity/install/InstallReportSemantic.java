package com.entities.entity.install;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportSemanticDAO;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportSemantic;
import com.entities.utilities.hibernate.VersionAuditor;

public class InstallReportSemantic {

	private ApplicationContext applicationContext;

	public InstallReportSemantic(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void install(ReportCatalog reportCatalog) {

		VersionAuditor versionAdmin = new VersionAuditor("admin");

		List<ReportSemantic> reportSemantics = new ArrayList<ReportSemantic>();

		// (2) <Version>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(2) is mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"Version\", \"2\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(2)",
						versionAdmin));

		// (12) <AIFMReportingObligationChangeQuarter>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(12) is mandatory/forbidden",
						"if( "
								+ "\n ( ( ReportUtilities.searchData(reportDatas, \"AIFMReportingObligationChangeFrequencyCode\", \"10\", null) != null "
								+ "\n || ReportUtilities.searchData(reportDatas, \"AIFMReportingObligationChangeContentsCode\", \"11\", null) != null "
								+ "\n ) && ReportUtilities.searchData(reportDatas, \"AIFMReportingObligationChangeQuarter\", \"12\", null) != null )"
								+ "\n || (ReportUtilities.searchData(reportDatas, \"AIFMReportingObligationChangeFrequencyCode\", \"10\", null) == null "
								+ "\n && ReportUtilities.searchData(reportDatas, \"AIFMReportingObligationChangeContentsCode\", \"11\", null) == null "
								+ "\n &&  ReportUtilities.searchData(reportDatas, \"AIFMReportingObligationChangeQuarter\", \"12\", null) == null)"
								+ ")" + "\n result = \"ok\"",
						null,
						"Fill field(12) only when field(10) or field(11) have content.",
						versionAdmin));

		// (25) <AIFMNationalCode>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(25) is mandatory",
						"if ( "
								+ "(ReportUtilities.searchData(reportDatas, \"ReportingMemberState\", \"24\", null) != null"
								+ " && ReportUtilities.searchData(reportDatas, \"AIFMNationalCode\", \"25\", null) != null)"
								+ " || ReportUtilities.searchData(reportDatas, \"ReportingMemberState\", \"24\", null) == null) "
								+ "\n result = \"ok\"", null,
						"Fill field(25) if field(24) has content", versionAdmin));

		ReportSemanticDAO reportSemanticDAO = (ReportSemanticDAO) applicationContext
				.getBean("reportSemanticDAO");

		for (ReportSemantic reportSemanticExample : reportSemantics) {
			reportSemanticDAO.create(reportSemanticExample);
		}

	}
}
