package com.entities.entity.loader;

// Generated 11-feb-2015 16:49:54 by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.entities.entity.reportingtool.Department;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

/**
 * LoadFile generated by hbm2java
 */
@Entity
@Table(name = "T_LOAD_FILE")
public class LoadFile implements VersionableAdapter {

	private long id;
	private Department department;
	private FileConfig fileConfig;
	private Date loadFileDate;
	private String loadFileName;
	private List<LoadError> loadErrors = new ArrayList<LoadError>();
	private List<LoadRaw> loadRaws = new ArrayList<LoadRaw>();
	private List<ReportExecution> reportExecutions;

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public LoadFile() {
	}

	public LoadFile(long loadFileId, Department department,
			FileConfig fileConfig, VersionAuditor versionAuditor) {
		this.id = loadFileId;
		this.department = department;
		this.fileConfig = fileConfig;
		this.versionAuditor = versionAuditor;
	}

	public LoadFile(Department department, FileConfig fileConfig,
			Date loadFileDate, String loadFileName, List<LoadError> loadErrors,
			List<LoadRaw> loadRaws, VersionAuditor versionAuditor) {
		this.department = department;
		this.fileConfig = fileConfig;
		this.loadFileDate = loadFileDate;
		this.loadFileName = loadFileName;
		this.loadErrors = loadErrors;
		this.loadRaws = loadRaws;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@SequenceGenerator(name = "GEN_LOAD_FILE", sequenceName = "SEQ_LOAD_FILE", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_LOAD_FILE")
	@Column(name = "LOAD_FILE_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long loadFileId) {
		this.id = loadFileId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_ID", nullable = false, foreignKey = @ForeignKey(name = "T_LOAD_FILE_FK_DPTO"))
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FILE_CONFIG_ID", nullable = false, foreignKey = @ForeignKey(name = "T_LOAD_FILE_FK_FILE_CONFIG"))
	public FileConfig getFileConfig() {
		return this.fileConfig;
	}

	public void setFileConfig(FileConfig fileConfig) {
		this.fileConfig = fileConfig;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LOAD_FILE_DATE", length = 7)
	public Date getLoadFileDate() {
		return this.loadFileDate;
	}

	public void setLoadFileDate(Date loadFileDate) {
		this.loadFileDate = loadFileDate;
	}

	@Column(name = "LOAD_FILE_NAME")
	public String getLoadFileName() {
		return this.loadFileName;
	}

	public void setLoadFileName(String loadFileName) {
		this.loadFileName = loadFileName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loadFile")
	@Cascade({ CascadeType.SAVE_UPDATE })
	public List<LoadError> getLoadErrors() {
		return this.loadErrors;
	}

	public void setLoadErrors(List<LoadError> loadErrors) {
		this.loadErrors = loadErrors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loadFile")
	@Cascade({ CascadeType.SAVE_UPDATE })
	@OrderBy("loadLineNumber ASC")
	public List<LoadRaw> getLoadRaws() {
		return this.loadRaws;
	}

	public void setLoadRaws(List<LoadRaw> loadRaws) {
		this.loadRaws = loadRaws;
	}

	@ManyToMany(mappedBy = "loadFiles")
	public List<ReportExecution> getReportExecutions() {
		return this.reportExecutions;
	}

	public void setReportExecutions(List<ReportExecution> reportExecutions) {
		this.reportExecutions = reportExecutions;
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
		if (object instanceof LoadFile) {
			return ((LoadFile) object).getDepartment().equals(this.department)
					&& ((LoadFile) object).getFileConfig().equals(
							this.fileConfig)
					&& ((LoadFile) object).getLoadFileName().equals(
							this.loadFileName);

		}
		return false;
	}
}
