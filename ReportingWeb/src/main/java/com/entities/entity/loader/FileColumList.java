package com.entities.entity.loader;

// Generated 11-feb-2015 16:49:54 by Hibernate Tools 4.0.0

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

/**
 * FileColumList generated by hbm2java
 */
@Entity
@Table(name = "T_FILE_COLUM_LIST")
public class FileColumList implements VersionableAdapter {

	private long id;
	private FileColum fileColum;
	private String fileColumListType;
	private String fileColumListOrig;
	private String fileColumListDest;

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public FileColumList() {
	}

	public FileColumList(long fileColumListId, FileColum fileColum,
			VersionAuditor versionAuditor) {
		this.id = fileColumListId;
		this.fileColum = fileColum;
		this.versionAuditor = versionAuditor;
	}

	public FileColumList(FileColum fileColum, String fileColumListType,
			String fileColumListOrig, String fileColumListDest,
			VersionAuditor versionAuditor) {
		this.fileColum = fileColum;
		this.fileColumListType = fileColumListType;
		this.fileColumListOrig = fileColumListOrig;
		this.fileColumListDest = fileColumListDest;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@SequenceGenerator(name = "GEN_FILE_COLUM_LIST", sequenceName = "SEQ_FILE_COLUM_LIST", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_FILE_COLUM_LIST")
	@Column(name = "FILE_COLUM_LIST_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long fileColumListId) {
		this.id = fileColumListId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FILE_COLUM_ID", nullable = false, foreignKey=@ForeignKey(name="T_FILE_COLUM_LIST_FK_FILE_COL"))
	public FileColum getFileColum() {
		return this.fileColum;
	}

	public void setFileColum(FileColum fileColum) {
		this.fileColum = fileColum;
	}

	@Column(name = "FILE_COLUM_LIST_TYPE", length = 40)
	public String getFileColumListType() {
		return this.fileColumListType;
	}

	public void setFileColumListType(String fileColumListType) {
		this.fileColumListType = fileColumListType;
	}

	@Column(name = "FILE_COLUM_LIST_ORIG")
	public String getFileColumListOrig() {
		return this.fileColumListOrig;
	}

	public void setFileColumListOrig(String fileColumListOrig) {
		this.fileColumListOrig = fileColumListOrig;
	}

	@Column(name = "FILE_COLUM_LIST_DEST")
	public String getFileColumListDest() {
		return this.fileColumListDest;
	}

	public void setFileColumListDest(String fileColumListDest) {
		this.fileColumListDest = fileColumListDest;
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
