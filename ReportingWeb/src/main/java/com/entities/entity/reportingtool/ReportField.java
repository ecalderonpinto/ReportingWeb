package com.entities.entity.reportingtool;

// Generated 11-feb-2015 17:15:14 by Hibernate Tools 4.0.0

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

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
	private String reportFieldVersion;
	private Set<FileColum> fileColums = new HashSet(0);
	private Set<ReportData> reportDatas = new HashSet(0);
	private Set<ReportCustom> reportCustoms = new HashSet(0);

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public ReportField() {
	}

	public ReportField(long reportFieldId, ReportCatalog reportCatalog,
			String reportFieldType, String reportFieldName, BigInteger reportFieldNum,
			VersionAuditor versionAuditor) {
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
			String reportFieldVersion, Set<FileColum> fileColums,
			Set<ReportData> reportDatas, Set<ReportCustom> reportCustoms,
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

	@Column(name = "REPORT_FIELD_NUM", nullable = true)
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
	public Set<FileColum> getFileColums() {
		return this.fileColums;
	}

	public void setFileColums(Set<FileColum> fileColums) {
		this.fileColums = fileColums;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reportField")
	public Set<ReportData> getReportDatas() {
		return this.reportDatas;
	}

	public void setReportDatas(Set<ReportData> reportDatas) {
		this.reportDatas = reportDatas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reportField")
	public Set<ReportCustom> getReportCustoms() {
		return this.reportCustoms;
	}

	public void setReportCustoms(Set<ReportCustom> reportCustoms) {
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

}
