package com.entities.entity.install;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.entities.dao.reportingtool.ReportFieldListDAO;
import com.entities.entity.reportingtool.ReportFieldList;
import com.entities.utilities.hibernate.VersionAuditor;

public class InstallReportList {

	private ApplicationContext applicationContext;
	
	public InstallReportList(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	/**
	 * Process to install in database reportFieldList for all AIFMD reports
	 * 
	 * @param applicationContext
	 */
	public void install() {

		List<ReportFieldList> reportFieldList = new ArrayList<ReportFieldList>();

		VersionAuditor versionAdmin = new VersionAuditor("admin");

		ReportFieldListDAO reportFieldListDAO = (ReportFieldListDAO) applicationContext
				.getBean("reportFieldListDAO");
		reportFieldListDAO.deleteAll();

		reportFieldList.add(new ReportFieldList("BOOLEAN", "true", "true",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("BOOLEAN", "false", "false",
				versionAdmin));

		reportFieldList.add(new ReportFieldList("AIFMasterFeederStatusType",
				"MASTER", "MASTER", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFMasterFeederStatusType",
				"FEEDER", "FEEDER", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFMasterFeederStatusType",
				"NONE", "NONE", versionAdmin));

		reportFieldList.add(new ReportFieldList("AIFTypeType", "HFND", "HFND",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFTypeType", "PEQF", "PEQF",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFTypeType", "REST", "REST",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFTypeType", "FOFS", "FOFS",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFTypeType", "OTHR", "OTHR",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("AIFTypeType", "NONE", "NONE",
				versionAdmin));

		reportFieldList.add(new ReportFieldList("AIIDerivativeTypeType", "O",
				"O", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIIDerivativeTypeType", "F",
				"F", versionAdmin));

		reportFieldList.add(new ReportFieldList("AIIPutCallIdentifierType", "P",
				"P", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIIPutCallIdentifierType", "C",
				"C", versionAdmin));
		reportFieldList.add(new ReportFieldList("AIIPutCallIdentifierType", "F",
				"F", versionAdmin));

		reportFieldList.add(new ReportFieldList("AlternateTextType", "NA", "NA",
				versionAdmin));

		reportFieldList.add(new ReportFieldList("AssetMacroTypeType", "SEC",
				"SEC", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetMacroTypeType", "DER",
				"DER", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetMacroTypeType", "CIU",
				"CIU", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetMacroTypeType", "PHY",
				"PHY", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetMacroTypeType", "OTH",
				"OTH", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetMacroTypeType", "NTA",
				"NTA", versionAdmin));

		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CSH",
				"SEC_CSH", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_LEQ",
				"SEC_LEQ", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_UEQ",
				"SEC_UEQ", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CPN",
				"SEC_CPN", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CPI",
				"SEC_CPI", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_SBD",
				"SEC_SBD", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_MBN",
				"SEC_MBN", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CBN",
				"SEC_CBN", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_CBI",
				"SEC_CBI", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_LON",
				"SEC_LON", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "SEC_SSP",
				"SEC_SSP", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "DER_EQD",
				"DER_EQD", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "DER_FID",
				"DER_FID", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "DER_CDS",
				"DER_CDS", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "DER_FEX",
				"DER_FEX", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "DER_IRD",
				"DER_IRD", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "DER_CTY",
				"DER_CTY", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "DER_OTH",
				"DER_OTH", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "PHY_RES",
				"PHY_RES", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "PHY_CTY",
				"PHY_CTY", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "PHY_TIM",
				"PHY_TIM", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "PHY_ART",
				"PHY_ART", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "PHY_TPT",
				"PHY_TPT", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "PHY_OTH",
				"PHY_OTH", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "CIU_OAM",
				"CIU_OAM", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "CIU_NAM",
				"CIU_NAM", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "OTH_OTH",
				"OTH_OTH", versionAdmin));
		reportFieldList.add(new ReportFieldList("AssetTypeType", "NTA_NTA",
				"NTA_NTA", versionAdmin));

		reportFieldList.add(new ReportFieldList("CancelledRecordFlagType", "C",
				"C", versionAdmin));
		reportFieldList.add(new ReportFieldList("CancelledRecordFlagType", "D",
				"D", versionAdmin));

		reportFieldList.add(new ReportFieldList("FilingTypeType", "AMND",
				"AMND", versionAdmin));
		reportFieldList.add(new ReportFieldList("FilingTypeType", "INIT",
				"INIT", versionAdmin));

		reportFieldList.add(new ReportFieldList("FundOfFundsStrategyTypeType",
				"FOFS_FHFS", "FOFS_FHFS", versionAdmin));
		reportFieldList.add(new ReportFieldList("FundOfFundsStrategyTypeType",
				"FOFS_PRIV", "FOFS_PRIV", versionAdmin));
		reportFieldList.add(new ReportFieldList("FundOfFundsStrategyTypeType",
				"OTHR_FOFS", "OTHR_FOFS", versionAdmin));

		reportFieldList.add(new ReportFieldList("FXEURReferenceRateTypeType",
				"ECB", "ECB", versionAdmin));
		reportFieldList.add(new ReportFieldList("FXEURReferenceRateTypeType",
				"OTH", "OTH", versionAdmin));

		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"NONE", "NONE", versionAdmin));

		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EQTY_LGBS", "EQTY_LGBS", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EQTY_LGST", "EQTY_LGST", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EQTY_MTNL", "EQTY_MTNL", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EQTY_STBS", "EQTY_STBS", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"RELV_FXIA", "RELV_FXIA", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"RELV_CBAR", "RELV_CBAR", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"RELV_VLAR", "RELV_VLAR", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EVDR_DSRS", "EVDR_DSRS", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EVDR_RAMA", "EVDR_RAMA", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"EVDR_EYSS", "EVDR_EYSS", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"CRED_LGST", "CRED_LGST", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"CRED_ABLG", "CRED_ABLG", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"MACR_MACR", "MACR_MACR", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"MANF_CTAF", "MANF_CTAF", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"MANF_CTAQ", "MANF_CTAQ", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"MULT_HFND", "MULT_HFND", versionAdmin));
		reportFieldList.add(new ReportFieldList("HedgeFundStrategyTypeType",
				"OTHR_HFND", "OTHR_HFND", versionAdmin));

		reportFieldList.add(new ReportFieldList("InstrumentCodeTypeType",
				"ISIN", "ISIN", versionAdmin));
		reportFieldList.add(new ReportFieldList("InstrumentCodeTypeType", "AII",
				"AII", versionAdmin));
		reportFieldList.add(new ReportFieldList("InstrumentCodeTypeType",
				"NONE", "NONE", versionAdmin));

		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType", "NFCO",
				"NFCO", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType", "BANK",
				"BANK", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType", "INSC",
				"INSC", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType", "OFIN",
				"OFIN", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType", "PFND",
				"PFND", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType", "GENG",
				"GENG", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType", "OCIU",
				"OCIU", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType", "HHLD",
				"HHLD", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType", "UNKN",
				"UNKN", versionAdmin));
		reportFieldList.add(new ReportFieldList("InvestorGroupTypeType", "NONE",
				"NONE", versionAdmin));

		reportFieldList
				.add(new ReportFieldList("InvestorRedemptionFrequencyType",
						"NONE", "NONE", versionAdmin));

		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "D", "D", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "W", "W", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "F", "F", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "M", "M", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "Q", "Q", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "H", "H", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "Y", "Y", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "O", "O", versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"InvestorRedemptionFrequencyType", "N", "N", versionAdmin));

		reportFieldList.add(new ReportFieldList("MarketCodeTypeWithNOTType",
				"NOT", "NOT", versionAdmin));
		reportFieldList.add(new ReportFieldList("MarketCodeTypeWithNOTType",
				"MIC", "MIC", versionAdmin));
		reportFieldList.add(new ReportFieldList("MarketCodeTypeWithNOTType",
				"OTC", "OTC", versionAdmin));
		reportFieldList.add(new ReportFieldList("MarketCodeTypeWithNOTType",
				"XXX", "XXX", versionAdmin));

		reportFieldList.add(new ReportFieldList("MarketCodeTypeWithoutNOTType",
				"MIC", "MIC", versionAdmin));
		reportFieldList.add(new ReportFieldList("MarketCodeTypeWithoutNOTType",
				"OTC", "OTC", versionAdmin));
		reportFieldList.add(new ReportFieldList("MarketCodeTypeWithoutNOTType",
				"XXX", "XXX", versionAdmin));

		reportFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_COMF", "OTHR_COMF", versionAdmin));
		reportFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_EQYF", "OTHR_EQYF", versionAdmin));
		reportFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_FXIF", "OTHR_FXIF", versionAdmin));
		reportFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_INFF", "OTHR_INFF", versionAdmin));
		reportFieldList.add(new ReportFieldList("OtherFundStrategyTypeType",
				"OTHR_OTHF", "OTHR_OTHF", versionAdmin));

		reportFieldList.add(new ReportFieldList("PositionTypeType", "L", "L",
				versionAdmin));
		reportFieldList.add(new ReportFieldList("PositionTypeType", "S", "S",
				versionAdmin));

		reportFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "VENT_CAPL", "VENT_CAPL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "GRTH_CAPL", "GRTH_CAPL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "MZNE_CAPL", "MZNE_CAPL",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "MULT_PEQF", "MULT_PEQF",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"PrivateEquityFundStrategyTypeType", "OTHR_PEQF", "OTHR_PEQF",
				versionAdmin));

		reportFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "RESL_REST", "RESL_REST",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "COML_REST", "COML_REST",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "INDL_REST", "INDL_REST",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "MULT_REST", "MULT_REST",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"RealEstateFundStrategyTypeType", "OTHR_REST", "OTHR_REST",
				versionAdmin));

		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "YH", "YH",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "YQ", "YQ",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "HY", "HY",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "HQ", "HQ",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "QY", "QY",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "QH", "QH",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "NQ", "NQ",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "NH", "NH",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeFrequencyCodeType", "NY", "NY",
				versionAdmin));

		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeQuarterType", "Q1", "Q1",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeQuarterType", "Q2", "Q2",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeQuarterType", "Q3", "Q3",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"ReportingObligationChangeQuarterType", "Q4", "Q4",
				versionAdmin));

		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "Q1",
				"Q1", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "Q2",
				"Q2", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "Q3",
				"Q3", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "Q4",
				"Q4", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "H1",
				"H1", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "H2",
				"H2", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "Y1",
				"Y1", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "X1",
				"X1", versionAdmin));
		reportFieldList.add(new ReportFieldList("ReportingPeriodTypeType", "X2",
				"X2", versionAdmin));

		reportFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_EQTY_DELTA", "NET_EQTY_DELTA", versionAdmin));
		reportFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_DV01", "NET_DV01", versionAdmin));
		reportFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_CS01", "NET_CS01", versionAdmin));
		reportFieldList.add(new ReportFieldList("RiskMeasureTypeType", "VAR",
				"VAR", versionAdmin));
		reportFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"VEGA_EXPO", "VEGA_EXPO", versionAdmin));
		reportFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_FX_DELTA", "NET_FX_DELTA", versionAdmin));
		reportFieldList.add(new ReportFieldList("RiskMeasureTypeType",
				"NET_CTY_DELTA", "NET_CTY_DELTA", versionAdmin));

		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CSH_CODP", "SEC_CSH_CODP", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CSH_COMP", "SEC_CSH_COMP", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CSH_OTHD", "SEC_CSH_OTHD", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CSH_OTHC", "SEC_CSH_OTHC", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_LEQ_IFIN", "SEC_LEQ_IFIN", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_LEQ_OTHR", "SEC_LEQ_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_UEQ_UEQY", "SEC_UEQ_UEQY", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CPN_INVG", "SEC_CPN_INVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CPN_NIVG", "SEC_CPN_NIVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CPI_INVG", "SEC_CPI_INVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CPI_NIVG", "SEC_CPI_NIVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_EUBY", "SEC_SBD_EUBY", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_EUBM", "SEC_SBD_EUBM", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_NOGY", "SEC_SBD_NOGY", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_NOGM", "SEC_SBD_NOGM", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_EUGY", "SEC_SBD_EUGY", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SBD_EUGM", "SEC_SBD_EUGM", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_MBN_MNPL", "SEC_MBN_MNPL", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CBN_INVG", "SEC_CBN_INVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CBN_NIVG", "SEC_CBN_NIVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CBI_INVG", "SEC_CBI_INVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_CBI_NIVG", "SEC_CBI_NIVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_LON_LEVL", "SEC_LON_LEVL", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_LON_OTHL", "SEC_LON_OTHL", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_SABS", "SEC_SSP_SABS", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_RMBS", "SEC_SSP_RMBS", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_CMBS", "SEC_SSP_CMBS", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_AMBS", "SEC_SSP_AMBS", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_ABCP", "SEC_SSP_ABCP", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_CDOC", "SEC_SSP_CDOC", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_STRC", "SEC_SSP_STRC", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_SETP", "SEC_SSP_SETP", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"SEC_SSP_OTHS", "SEC_SSP_OTHS", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_EQD_FINI", "DER_EQD_FINI", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_EQD_OTHD", "DER_EQD_OTHD", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_FID_FIXI", "DER_FID_FIXI", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_SNFI", "DER_CDS_SNFI", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_SNSO", "DER_CDS_SNSO", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_SNOT", "DER_CDS_SNOT", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_INDX", "DER_CDS_INDX", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_EXOT", "DER_CDS_EXOT", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CDS_OTHR", "DER_CDS_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_FEX_INVT", "DER_FEX_INVT", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_FEX_HEDG", "DER_FEX_HEDG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_IRD_INTR", "DER_IRD_INTR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_ECOL", "DER_CTY_ECOL", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_ENNG", "DER_CTY_ENNG", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_ENPW", "DER_CTY_ENPW", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_ENOT", "DER_CTY_ENOT", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_PMGD", "DER_CTY_PMGD", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_PMOT", "DER_CTY_PMOT", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_OTIM", "DER_CTY_OTIM", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_OTLS", "DER_CTY_OTLS", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_OTAP", "DER_CTY_OTAP", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_CTY_OTHR", "DER_CTY_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"DER_OTH_OTHR", "DER_OTH_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_RES_RESL", "PHY_RES_RESL", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_RES_COML", "PHY_RES_COML", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_RES_OTHR", "PHY_RES_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_CTY_PCTY", "PHY_CTY_PCTY", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_TIM_PTIM", "PHY_TIM_PTIM", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_ART_PART", "PHY_ART_PART", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_TPT_PTPT", "PHY_TPT_PTPT", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"PHY_OTH_OTHR", "PHY_OTH_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_OAM_MMFC", "CIU_OAM_MMFC", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_OAM_AETF", "CIU_OAM_AETF", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_OAM_OTHR", "CIU_OAM_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_NAM_MMFC", "CIU_NAM_MMFC", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_NAM_AETF", "CIU_NAM_AETF", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"CIU_NAM_OTHR", "CIU_NAM_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"OTH_OTH_OTHR", "OTH_OTH_OTHR", versionAdmin));
		reportFieldList.add(new ReportFieldList("SubAssetTypeType",
				"NTA_NTA_NOTA", "NTA_NTA_NOTA", versionAdmin));

		reportFieldList.add(new ReportFieldList("TransactionTypeType", "ACAP",
				"ACAP", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "BOUT",
				"BOUT", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "CONS",
				"CONS", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "CDIV",
				"CDIV", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "ESOP",
				"ESOP", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "GCAP",
				"GCAP", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "RCAP",
				"RCAP", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "SLIQ",
				"SLIQ", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "TURN",
				"TURN", versionAdmin));
		reportFieldList.add(new ReportFieldList("TransactionTypeType", "OTHR",
				"OTHR", versionAdmin));

		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CSH_CSH", "SEC_CSH_CSH", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_LEQ_LEQ", "SEC_LEQ_LEQ", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_UEQ_UEQ", "SEC_UEQ_UEQ", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CPN_IVG", "SEC_CPN_IVG", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CPN_NIG", "SEC_CPN_NIG", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CPI_CPI", "SEC_CPI_CPI", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_SBD_EUB", "SEC_SBD_EUB", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_SBD_NEU", "SEC_SBD_NEU", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_MUN_MUN", "SEC_MUN_MUN", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_CBD_CBD", "SEC_CBD_CBD", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_LON_LON", "SEC_LON_LON", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"SEC_SSP_SSP", "SEC_SSP_SSP", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_EQD_EQD", "DER_EQD_EQD", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_FID_FID", "DER_FID_FID", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_CDS_CDS", "DER_CDS_CDS", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_FEX_INV", "DER_FEX_INV", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_FEX_HED", "DER_FEX_HED", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_IRD_IRD", "DER_IRD_IRD", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_CTY_CTY", "DER_CTY_CTY", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"DER_OTH_OTH", "DER_OTH_OTH", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_RES_RES", "PHY_RES_RES", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_CTY_CTY", "PHY_CTY_CTY", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_TIM_TIM", "PHY_TIM_TIM", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_ART_ART", "PHY_ART_ART", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_TPT_TPT", "PHY_TPT_TPT", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"PHY_OTH_OTH", "PHY_OTH_OTH", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"CIU_CIU_CIU", "CIU_CIU_CIU", versionAdmin));
		reportFieldList.add(new ReportFieldList("TurnoverSubAssetTypeType",
				"OTH_OTH_OTH", "OTH_OTH_OTH", versionAdmin));

		reportFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"V_SMALL", "V_SMALL", versionAdmin));
		reportFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"SMALL", "SMALL", versionAdmin));
		reportFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"LOW_MID_MKT", "LOW_MID_MKT", versionAdmin));
		reportFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"UP_MID_MKT", "UP_MID_MKT", versionAdmin));
		reportFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"L_CAP", "L_CAP", versionAdmin));
		reportFieldList.add(new ReportFieldList("TypicalPositionSizeType",
				"M_CAP", "M_CAP", versionAdmin));

		reportFieldList.add(new ReportFieldList(
				"VARCalculationMethodCodeTypeType", "HISTO", "HISTO",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"VARCalculationMethodCodeTypeType", "CARLO", "CARLO",
				versionAdmin));
		reportFieldList.add(new ReportFieldList(
				"VARCalculationMethodCodeTypeType", "PARAM", "PARAM",
				versionAdmin));

		for (ReportFieldList reportFieldListExample : reportFieldList) {
			reportFieldListDAO.create(reportFieldListExample);
		}

	}
}
