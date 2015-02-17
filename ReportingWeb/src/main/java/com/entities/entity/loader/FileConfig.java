package com.entities.entity.loader;

// Generated 11-feb-2015 16:49:54 by Hibernate Tools 4.0.0

import com.entities.entity.reportingtool.Department;
import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * FileConfig generated by hbm2java
 */
@Entity
@Table(name = "T_FILE_CONFIG")
public class FileConfig implements VersionableAdapter {

	private long id;
	private Department department;
	private String fileType;
	private String fileConfigName;
	private String fileSeparator;
	private String fileFormatLine;
	private String fileCron;
	private String filePath;
	private Set<LoadFile> loadFiles = new HashSet(0);
	private Set<FileColum> fileColums = new HashSet(0);

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public FileConfig() {
	}

	public FileConfig(long fileConfigId, String fileType,
			String fileConfigName, VersionAuditor versionAuditor) {
		this.id = fileConfigId;
		this.fileType = fileType;
		this.fileConfigName = fileConfigName;
		this.versionAuditor = versionAuditor;
	}

	public FileConfig(Department department, String fileType,
			String fileConfigName, String fileSeparator, String fileFormatLine,
			String fileCron, String filePath, Set<LoadFile> loadFiles,
			Set<FileColum> fileColums, VersionAuditor versionAuditor) {
		this.department = department;
		this.fileType = fileType;
		this.fileConfigName = fileConfigName;
		this.fileSeparator = fileSeparator;
		this.fileFormatLine = fileFormatLine;
		this.fileCron = fileCron;
		this.filePath = filePath;
		this.loadFiles = loadFiles;
		this.fileColums = fileColums;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@SequenceGenerator(name = "GEN_FILE_CONFIG", sequenceName = "SEQ_FILE_CONFIG", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_FILE_CONFIG")
	@Column(name = "FILE_CONFIG_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long fileConfigId) {
		this.id = fileConfigId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_ID", foreignKey=@ForeignKey(name="T_FILE_CONFIG_FK_DPTO"))
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name = "FILE_TYPE", nullable = false, length = 40)
	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "FILE_CONFIG_NAME", nullable = false)
	public String getFileConfigName() {
		return this.fileConfigName;
	}

	public void setFileConfigName(String fileConfigName) {
		this.fileConfigName = fileConfigName;
	}

	@Column(name = "FILE_SEPARATOR", length = 2)
	public String getFileSeparator() {
		return this.fileSeparator;
	}

	public void setFileSeparator(String fileSeparator) {
		this.fileSeparator = fileSeparator;
	}

	@Column(name = "FILE_FORMAT_LINE", length = 40)
	public String getFileFormatLine() {
		return this.fileFormatLine;
	}

	public void setFileFormatLine(String fileFormatLine) {
		this.fileFormatLine = fileFormatLine;
	}

	@Column(name = "FILE_CRON")
	public String getFileCron() {
		return this.fileCron;
	}

	public void setFileCron(String fileCron) {
		this.fileCron = fileCron;
	}

	@Column(name = "FILE_PATH")
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fileConfig")
	public Set<LoadFile> getLoadFiles() {
		return this.loadFiles;
	}

	public void setLoadFiles(Set<LoadFile> loadFiles) {
		this.loadFiles = loadFiles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fileConfig")
	public Set<FileColum> getFileColums() {
		return this.fileColums;
	}

	public void setFileColums(Set<FileColum> fileColums) {
		this.fileColums = fileColums;
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
