package com.entities.entity.reportingtool;

// Generated 11-feb-2015 16:49:54 by Hibernate Tools 4.0.0

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.entities.entity.loader.FileConfig;
import com.entities.entity.loader.LoadFile;
import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

/**
 * Department generated by hbm2java
 */
@Entity
@Table(name = "T_DEPARTMENT")
public class Department implements VersionableAdapter {

	private long id;
	private Company company;
	private String departmentName;
	private String departmentCode;
	private String departmentDesc;
	private String departmentCountry;
	private List<LoadFile> loadFiles = new ArrayList<LoadFile>();
	private List<FundGroup> fundGroups = new ArrayList<FundGroup>();
	private List<FileConfig> fileConfigs = new ArrayList<FileConfig>();

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public Department() {
	}

	public Department(long departmentId, Company company,
			String departmentName, VersionAuditor versionAuditor) {
		this.id = departmentId;
		this.company = company;
		this.departmentName = departmentName;
		this.versionAuditor = versionAuditor;
	}

	public Department(Company company, String departmentName,
			String departmentCode, String departmentDesc,
			String departmentCountry, List<LoadFile> loadFiles,
			List<FundGroup> fundGroups, List<FileConfig> fileConfigs,
			VersionAuditor versionAuditor) {
		this.company = company;
		this.departmentName = departmentName;
		this.departmentCode = departmentCode;
		this.departmentDesc = departmentDesc;
		this.departmentCountry = departmentCountry;
		this.loadFiles = loadFiles;
		this.fundGroups = fundGroups;
		this.fileConfigs = fileConfigs;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@SequenceGenerator(name = "GEN_DEPARTMENT", sequenceName = "SEQ_DEPARTMENT", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_DEPARTMENT")
	@Column(name = "DEPARTMENT_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long departmentId) {
		this.id = departmentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPANY_ID", nullable = false, foreignKey = @ForeignKey(name = "T_DEPARTMENT_FK_COMPANY"))
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "DEPARTMENT_NAME", nullable = false)
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(name = "DEPARTMENT_CODE", length = 40)
	public String getDepartmentCode() {
		return this.departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	@Column(name = "DEPARTMENT_DESC")
	public String getDepartmentDesc() {
		return this.departmentDesc;
	}

	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}

	@Column(name = "DEPARTMENT_COUNTRY")
	public String getDepartmentCountry() {
		return this.departmentCountry;
	}

	public void setDepartmentCountry(String departmentCountry) {
		this.departmentCountry = departmentCountry;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	public List<LoadFile> getLoadFiles() {
		return this.loadFiles;
	}

	public void setLoadFiles(List<LoadFile> loadFiles) {
		this.loadFiles = loadFiles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	public List<FundGroup> getFundGroups() {
		return this.fundGroups;
	}

	public void setFundGroups(List<FundGroup> fundGroups) {
		this.fundGroups = fundGroups;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	public List<FileConfig> getFileConfigs() {
		return this.fileConfigs;
	}

	public void setFileConfigs(List<FileConfig> fileConfigs) {
		this.fileConfigs = fileConfigs;
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

	public VersionAuditor getVersionAuditor() {
		return versionAuditor;
	}

	public void setVersionAuditor(VersionAuditor _auditor) {
		this.versionAuditor = _auditor;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Department) {
			return ((Department) object).getDepartmentName().equals(
					this.departmentName)
					&& ((Department) object).getDepartmentCode().equals(
							this.departmentCode)
					&& ((Department) object).getDepartmentCountry().equals(
							this.departmentCountry)
					&& ((Department) object).getCompany().equals(this.company);

		}
		return false;
	}
}
