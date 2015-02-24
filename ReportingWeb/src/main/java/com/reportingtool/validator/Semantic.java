package com.reportingtool.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.reportingtool.ReportSemantic;

public class Semantic {

	private ApplicationContext applicationContext;

	public Semantic(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void checkSemantic(ReportExecution reportExecution) {
		List<ReportSemantic> reportSemantics = new ArrayList<ReportSemantic>(reportExecution
				.getReportCatalog().getReportSemantics());
		
		for(ReportSemantic reportSemantic : reportSemantics) {
			String rule = reportSemantic.getReportingSemanticRule();
		}
	}

}
