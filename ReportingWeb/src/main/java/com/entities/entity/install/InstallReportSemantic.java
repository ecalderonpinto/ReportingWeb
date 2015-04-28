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

	public void installAIFM(ReportCatalog reportCatalog) {

		VersionAuditor versionAdmin = new VersionAuditor("admin");

		List<ReportSemantic> reportSemantics = new ArrayList<ReportSemantic>();

		// (1) <ReportingMemberState>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(1) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"ReportingMemberState\", \"1\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(1)",
						versionAdmin));

		// (2) <Version>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(2) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"Version\", \"2\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(2)",
						versionAdmin));

		// (3) <CreationDateAndTime>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(3) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"CreationDateAndTime\", \"3\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(3)",
						versionAdmin));

		// (4) <FilingType>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(4) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"FilingType\", \"4\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(4)",
						versionAdmin));

		// (5) <AIFMContentType>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(5) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"AIFMContentType\", \"5\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(5)",
						versionAdmin));

		// (6) <ReportingPeriodStartDate>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(6) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"ReportingPeriodStartDate\", \"6\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(6)",
						versionAdmin));

		// (7) <ReportingPeriodEndDate>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(7) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"ReportingPeriodEndDate\", \"7\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(7)",
						versionAdmin));

		// (8) <ReportingPeriodType>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(8) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"ReportingPeriodType\", \"8\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(8)",
						versionAdmin));

		// (9) <ReportingPeriodYear>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(9) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"ReportingPeriodYear\", \"9\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(9)",
						versionAdmin));

		// (12) <AIFMReportingObligationChangeQuarter>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(12) mandatory/forbidden",
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

		// (13) <LastReportingFlag>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(13) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"LastReportingFlag\", \"13\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(13)",
						versionAdmin));

		// (14) <QuestionNumber>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(14) is mandatory",
						"if (ReportUtilities.dependencyRepeData(reportDatas, \"QuestionNumber\", \"14\", \"AssumptionDescription\", \"15\")) "
								+ "\n result = \"ok\"", null,
						"Fill field(14) is mandatory when field(15) has content.",
						versionAdmin));

		// (15) <AssumptionDescription>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(15) is mandatory",
						"if (ReportUtilities.dependencyRepeData(reportDatas, \"AssumptionDescription\", \"15\", \"QuestionNumber\", \"14\")) "
								+ "\n result = \"ok\"", null,
						"Fill field(15) is mandatory when field(14) has content.",
						versionAdmin));

		// (16) <AIFMReportingCode>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(16) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"AIFMReportingCode\", \"16\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(16)",
						versionAdmin));

		// (17) <AIFMJurisdiction>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(17) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"AIFMJurisdiction\", \"17\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(17)",
						versionAdmin));

		// (18) <AIFMNationalCode>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(18) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"AIFMNationalCode\", \"18\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(18)",
						versionAdmin));

		// (19) <AIFMName>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(19) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"AIFMName\", \"19\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(19)",
						versionAdmin));

		// (20) <AIFMEEAFlag>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(20) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"AIFMEEAFlag\", \"20\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(20)",
						versionAdmin));

		// (21) <AIFMNoReportingFlag>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(21) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"AIFMNoReportingFlag\", \"21\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(21)",
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

		// (26) <Ranking>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(26) is mandatory",
						"if (ReportUtilities.searchBlockList(reportDatas, \"Ranking\", \"26\").size() == 5) "
								+ "\n result = \"ok\"", null,
						"Fill field(26) has 5 occurences.", versionAdmin));

		// (27) <MarketCodeType>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(27) is mandatory",
						"if (ReportUtilities.searchBlockList(reportDatas, \"MarketCodeType\", \"27\").size() == 5) "
								+ "\n result = \"ok\"", null,
						"Fill field(27) has 5 occurences.", versionAdmin));

		// (28) <MarketCode>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(28) is mandatory",
						"if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"MarketCode\", \"28\", \"MarketCodeType\", \"27\", \"MIC\")) "
								+ "\n result = \"ok\"", null,
						"Fill field(28) is mandatory when field(27) = MIC.",
						versionAdmin));

		// (29) <AggregatedValueAmount>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(29) is mandatory",
						"if (ReportUtilities.dependencyRepeDataNot(reportDatas, \"AggregatedValueAmount\", \"29\", \"MarketCodeType\", \"27\", \"NOT\")) "
								+ "\n result = \"ok\"",
						null,
						"Fill field(29) is mandatory when field(27) different NOT.",
						versionAdmin));

		// (30) <Ranking>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(30) is mandatory",
						"if (ReportUtilities.searchBlockList(reportDatas, \"Ranking\", \"30\").size() == 5) "
								+ "\n result = \"ok\"", null,
						"Fill field(30) has 5 occurences.", versionAdmin));

		// (31) <SubAssetType>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(26) is mandatory",
						"if (ReportUtilities.searchBlockList(reportDatas, \"SubAssetType\", \"31\").size() == 5) "
								+ "\n result = \"ok\"", null,
						"Fill field(31) has 5 occurences.", versionAdmin));

		// (32) <AggregatedValueAmount>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(32) is mandatory",
						"if (ReportUtilities.dependencyRepeDataExist(reportDatas, \"AggregatedValueAmount\", \"32\", \"SubAssetType\", \"31\", \"NTA_NTA_NOTA\")) "
								+ "\n result = \"ok\"",
						null,
						"Fill field(32) is mandatory when field(31) = NTA_NTA_NOTA.",
						versionAdmin));

		// (33) <AUMAmountInEuro>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(33) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"AUMAmountInEuro\", \"33\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(33)",
						versionAdmin));

		// (34) <AUMAmountInBaseCurrency>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(34) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"AUMAmountInBaseCurrency\", \"34\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(34)",
						versionAdmin));

		// (35) <BaseCurrency>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(35) mandatory",
						"if ( ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"35\", null) != null) "
								+ "\n result = \"ok\"", null, "Fill field(35)",
						versionAdmin));

		// (36) <FXEURReferenceRateType>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(36) mandatory",
						"if (ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"35\", null) == null) {"
								+ "  result = \"ok\"; "
								+ "} else {"
								+ " if (ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"35\", null).equals(\"EUR\")"
								+ " &&  ReportUtilities.searchData(reportDatas, \"FXEURReferenceRateType\", \"36\", null) == null) {"
								+ " result = \"ok\"; "
								+ " } else {"
								+ "  if (!ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"35\", null).equals(\"EUR\")"
								+ " && ReportUtilities.searchData(reportDatas, \"FXEURReferenceRateType\", \"36\", null) != null)"
								+ " result = \"ok\"; " + " }" + "}", null,
						"Fill field(36) only when field(35) not EUR",
						versionAdmin));

		// (37) <FXEURRate>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(37) mandatory",
						"if (ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"35\", null) == null) {"
								+ "  result = \"ok\"; "
								+ "} else {"
								+ " if (ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"35\", null).equals(\"EUR\")"
								+ " &&  ReportUtilities.searchData(reportDatas, \"FXEURRate\", \"37\", null) == null) {"
								+ " result = \"ok\"; "
								+ " } else {"
								+ "  if (!ReportUtilities.searchData(reportDatas, \"BaseCurrency\", \"35\", null).equals(\"EUR\")"
								+ " && ReportUtilities.searchData(reportDatas, \"FXEURRate\", \"37\", null) != null)"
								+ " result = \"ok\"; " + " }" + "}", null,
						"Fill field(37) only when field(35) not EUR",
						versionAdmin));

		// (38) <FXEUROtherReferenceRateDescription>
		reportSemantics
				.add(new ReportSemantic(
						reportCatalog,
						"Field(38) mandatory",
						"if (ReportUtilities.searchData(reportDatas, \"FXEURReferenceRateType\", \"36\", null) == null) {"
								+ "  result = \"ok\"; "
								+ "} else {"
								+ " if (ReportUtilities.searchData(reportDatas, \"FXEURReferenceRateType\", \"36\", null).equals(\"OTH\")"
								+ " &&  ReportUtilities.searchData(reportDatas, \"FXEUROtherReferenceRateDescription\", \"38\", null) != null) {"
								+ " result = \"ok\"; "
								+ " } else {"
								+ "  if (!ReportUtilities.searchData(reportDatas, \"FXEURReferenceRateType\", \"36\", null).equals(\"OTH\")"
								+ " && ReportUtilities.searchData(reportDatas, \"FXEUROtherReferenceRateDescription\", \"38\", null) == null)"
								+ " result = \"ok\"; " + " }" + "}", null,
						"Fill field(38) only when field(36) is OTH",
						versionAdmin));

		ReportSemanticDAO reportSemanticDAO = (ReportSemanticDAO) applicationContext
				.getBean("reportSemanticDAO");

		for (ReportSemantic reportSemanticExample : reportSemantics) {
			reportSemanticDAO.create(reportSemanticExample);
		}

	}
}
