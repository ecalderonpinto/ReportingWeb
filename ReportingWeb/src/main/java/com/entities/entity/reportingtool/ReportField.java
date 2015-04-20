package com.entities.entity.reportingtool;

// Generated 11-feb-2015 17:15:14 by Hibernate Tools 4.0.0

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.entities.entity.loader.FileColum;
import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

/**
 * ReportField generated by hbm2java
 */
@Entity
@Table(name = "T_REPORT_FIELD")
public class ReportField implements VersionableAdapter {

	private long id;
	private ReportCatalog reportCatalog;
	private ReportField reportFieldParent;
	private String reportFieldType;
	private String reportFieldName;
	private BigInteger reportFieldNum;
	private String reportFieldFormat;
	private String reportFieldDesc;
	private String reportFieldSection;
	private String reportFieldMask;
	private String reportFieldOrder;
	private String reportFieldRepe;
	private boolean reportFieldMandatory;
	private boolean reportFieldDisabled;
	private String reportFieldVersion;
	private List<FileColum> fileColums = new ArrayList<FileColum>();
	private List<ReportData> reportDatas = new ArrayList<ReportData>();
	private List<ReportCustom> reportCustoms = new ArrayList<ReportCustom>();

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public ReportField() {
	}

	public ReportField(long reportFieldId, ReportCatalog reportCatalog,
			String reportFieldType, String reportFieldName,
			BigInteger reportFieldNum, VersionAuditor versionAuditor) {
		this.id = reportFieldId;
		this.reportCatalog = reportCatalog;
		this.reportFieldType = reportFieldType;
		this.reportFieldName = reportFieldName;
		this.reportFieldNum = reportFieldNum;
		this.versionAuditor = versionAuditor;
	}

	public ReportField(ReportCatalog reportCatalog,
			ReportField reportFieldParent, String reportFieldType,
			String reportFieldName, BigInteger reportFieldNum,
			String reportFieldFormat, String reportFieldDesc,
			String reportFieldSection, String reportFieldMask,
			String reportFieldOrder, String reportFieldRepe,
			String reportFieldVersion, List<FileColum> fileColums,
			List<ReportData> reportDatas, List<ReportCustom> reportCustoms,
			VersionAuditor versionAuditor) {
		this.reportCatalog = reportCatalog;
		this.reportFieldParent = reportFieldParent;
		this.reportFieldType = reportFieldType;
		this.reportFieldName = reportFieldName;
		this.reportFieldNum = reportFieldNum;
		this.reportFieldFormat = reportFieldFormat;
		this.reportFieldDesc = reportFieldDesc;
		this.reportFieldSection = reportFieldSection;
		this.reportFieldMask = reportFieldMask;
		this.reportFieldOrder = reportFieldOrder;
		this.reportFieldRepe = reportFieldRepe;
		this.reportFieldVersion = reportFieldVersion;
		this.fileColums = fileColums;
		this.reportDatas = reportDatas;
		this.reportCustoms = reportCustoms;
		this.versionAuditor = versionAuditor;
	}

	public ReportField(ReportCatalog reportCatalog,
			ReportField reportFieldParent, String reportFieldType,
			String reportFieldName, BigInteger reportFieldNum,
			String reportFieldFormat, String reportFieldDesc,
			String reportFieldSection, String reportFieldMask,
			String reportFieldOrder, String reportFieldRepe,
			String reportFieldVersion, List<FileColum> fileColums,
			List<ReportData> reportDatas, List<ReportCustom> reportCustoms,
			VersionAuditor versionAuditor, boolean reportFieldMandatory,
			boolean reportFieldDisabled) {
		this.reportCatalog = reportCatalog;
		this.reportFieldParent = reportFieldParent;
		this.reportFieldType = reportFieldType;
		this.reportFieldName = reportFieldName;
		this.reportFieldNum = reportFieldNum;
		this.reportFieldFormat = reportFieldFormat;
		this.reportFieldDesc = reportFieldDesc;
		this.reportFieldSection = reportFieldSection;
		this.reportFieldMask = reportFieldMask;
		this.reportFieldOrder = reportFieldOrder;
		this.reportFieldRepe = reportFieldRepe;
		this.reportFieldVersion = reportFieldVersion;
		this.fileColums = fileColums;
		this.reportDatas = reportDatas;
		this.reportCustoms = reportCustoms;
		this.versionAuditor = versionAuditor;
		this.reportFieldMandatory = reportFieldMandatory;
		this.reportFieldDisabled = reportFieldDisabled;
	}

	@Id
	@SequenceGenerator(name = "GEN_REPORT_FIELD", sequenceName = "SEQ_REPORT_FIELD", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_REPORT_FIELD")
	@Column(name = "REPORT_FIELD_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long reportFieldId) {
		this.id = reportFieldId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPORT_CATALOG_ID", nullable = false, foreignKey = @ForeignKey(name = "T_REPORT_FLD_FK_REPORT_CTLG"))
	public ReportCatalog getReportCatalog() {
		return this.reportCatalog;
	}

	public void setReportCatalog(ReportCatalog reportCatalog) {
		this.reportCatalog = reportCatalog;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPORT_FIELD_PARENT", nullable = true, foreignKey = @ForeignKey(name = "T_REPORT_FLD_FK_REPORT_FLD"))
	public ReportField getReportFieldParent() {
		return this.reportFieldParent;
	}

	public void setReportFieldParent(ReportField reportFieldParent) {
		this.reportFieldParent = reportFieldParent;
	}

	@Column(name = "REPORT_FIELD_TYPE", nullable = false, length = 40)
	public String getReportFieldType() {
		return this.reportFieldType;
	}

	public void setReportFieldType(String reportFieldType) {
		this.reportFieldType = reportFieldType;
	}

	@Column(name = "REPORT_FIELD_NAME", nullable = false)
	public String getReportFieldName() {
		return this.reportFieldName;
	}

	public void setReportFieldName(String reportFieldName) {
		this.reportFieldName = reportFieldName;
	}

	@Column(name = "REPORT_FIELD_NUM", nullable = false)
	public BigInteger getReportFieldNum() {
		return this.reportFieldNum;
	}

	public void setReportFieldNum(BigInteger reportFieldNum) {
		this.reportFieldNum = reportFieldNum;
	}

	@Column(name = "REPORT_FIELD_FORMAT")
	public String getReportFieldFormat() {
		return this.reportFieldFormat;
	}

	public void setReportFieldFormat(String reportFieldFormat) {
		this.reportFieldFormat = reportFieldFormat;
	}

	@Column(name = "REPORT_FIELD_DESC")
	public String getReportFieldDesc() {
		return this.reportFieldDesc;
	}

	public void setReportFieldDesc(String reportFieldDesc) {
		this.reportFieldDesc = reportFieldDesc;
	}

	@Column(name = "REPORT_FIELD_SECTION")
	public String getReportFieldSection() {
		return this.reportFieldSection;
	}

	public void setReportFieldSection(String reportFieldSection) {
		this.reportFieldSection = reportFieldSection;
	}

	@Column(name = "REPORT_FIELD_MASK")
	public String getReportFieldMask() {
		return this.reportFieldMask;
	}

	public void setReportFieldMask(String reportFieldMask) {
		this.reportFieldMask = reportFieldMask;
	}

	@Column(name = "REPORT_FIELD_ORDER", length = 40)
	public String getReportFieldOrder() {
		return this.reportFieldOrder;
	}

	public void setReportFieldOrder(String reportFieldOrder) {
		this.reportFieldOrder = reportFieldOrder;
	}

	@Column(name = "REPORT_FIELD_REPE", length = 2)
	public String getReportFieldRepe() {
		return this.reportFieldRepe;
	}

	public void setReportFieldRepe(String reportFieldRepe) {
		this.reportFieldRepe = reportFieldRepe;
	}

	@Column(name = "REPORT_FIELD_VERSION", length = 10)
	public String getReportFieldVersion() {
		return this.reportFieldVersion;
	}

	public void setReportFieldVersion(String reportFieldVersion) {
		this.reportFieldVersion = reportFieldVersion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reportField")
	public List<FileColum> getFileColums() {
		return this.fileColums;
	}

	public void setFileColums(List<FileColum> fileColums) {
		this.fileColums = fileColums;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reportField")
	public List<ReportData> getReportDatas() {
		return this.reportDatas;
	}

	public void setReportDatas(List<ReportData> reportDatas) {
		this.reportDatas = reportDatas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reportField")
	public List<ReportCustom> getReportCustoms() {
		return this.reportCustoms;
	}

	@Column(name = "REPORT_FIELD_MANDATORY")
	public boolean isReportFieldMandatory() {
		return reportFieldMandatory;
	}

	public void setReportFieldMandatory(boolean reportFieldMandatory) {
		this.reportFieldMandatory = reportFieldMandatory;
	}

	@Column(name = "REPORT_FIELD_DISABLED")
	public boolean isReportFieldDisabled() {
		return reportFieldDisabled;
	}

	public void setReportFieldDisabled(boolean reportFieldDisabled) {
		this.reportFieldDisabled = reportFieldDisabled;
	}

	public void setReportCustoms(List<ReportCustom> reportCustoms) {
		this.reportCustoms = reportCustoms;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public VersionAuditor getAuditor() {
		return versionAuditor;
	}

	public void setAuditor(VersionAuditor _auditor) {
		this.versionAuditor = _auditor;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof ReportField) {
			boolean ret = ((ReportField) object).getReportCatalog().equals(
					this.reportCatalog)
					&& ((ReportField) object).getReportFieldName().equals(
							this.reportFieldName)
					&& ((ReportField) object).getReportFieldNum().equals(
							this.reportFieldNum)
					&& ((ReportField) object).getReportFieldType().equals(
							this.reportFieldType);
			// if (((ReportField) object).getReportFieldParent() == null
			// && this.reportFieldParent == null) {
			// // return ret;
			// } else {
			// if (((ReportField) object).getReportFieldParent() == null
			// && this.reportFieldParent != null) {
			// ret = false;
			// } else {
			// if (((ReportField) object).getReportFieldParent() != null
			// && this.reportFieldParent == null) {
			// ret = false;
			// } else {
			// if (((ReportField) object).getReportFieldParent() != null) {
			//
			// System.out.println("DEBUG reportfield equals "
			// + ((ReportField) object)
			// .getReportFieldName()
			// + ((ReportField) object)
			// .getReportFieldParent()
			// .getReportFieldName()
			// + " igual a "
			// + this.reportFieldParent
			// .getReportFieldName());
			// }
			//
			// ret = ret
			// && ((ReportField) object)
			// .getReportFieldParent().equals(
			// this.reportFieldParent);
			// }
			// }
			// }
			return ret;
		}
		return false;
	}
}
