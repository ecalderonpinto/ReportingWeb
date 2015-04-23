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

		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(2) is mandatory",
						"result = ReportUtilities.searchData(reportExecution.getReportDatas(), \"Version\", \"2\", null)",
						null, "Fill field(2)", versionAdmin));

		
		
		ReportSemanticDAO reportSemanticDAO = (ReportSemanticDAO) applicationContext
				.getBean("reportSemanticDAO");

		for (ReportSemantic reportSemanticExample : reportSemantics) {
			reportSemanticDAO.create(reportSemanticExample);
		}

	}
}
