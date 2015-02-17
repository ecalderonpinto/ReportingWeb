package com.entities.entity.usermanager;

// Generated 11-feb-2015 16:49:54 by Hibernate Tools 4.0.0

import java.math.BigDecimal;

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
 * UserRolPermission generated by hbm2java
 */
@Entity
@Table(name = "T_USER_ROL_PERMISSION")
public class UserRolPermission implements VersionableAdapter {

	private long id;
	private UserRol userRol;
	private String rolPermissionName;
	private String rolPermissionDesc;
	private BigDecimal rolPermissionWeight;

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public UserRolPermission() {
	}

	public UserRolPermission(long rolPermissionId, UserRol userRol,
			String rolPermissionName, BigDecimal rolPermissionWeight,
			VersionAuditor versionAuditor) {
		this.id = rolPermissionId;
		this.userRol = userRol;
		this.rolPermissionName = rolPermissionName;
		this.rolPermissionWeight = rolPermissionWeight;
		this.versionAuditor = versionAuditor;
	}

	public UserRolPermission(UserRol userRol, String rolPermissionName,
			String rolPermissionDesc, BigDecimal rolPermissionWeight,
			VersionAuditor versionAuditor) {
		this.userRol = userRol;
		this.rolPermissionName = rolPermissionName;
		this.rolPermissionDesc = rolPermissionDesc;
		this.rolPermissionWeight = rolPermissionWeight;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@SequenceGenerator(name = "GEN_USER_ROL_PERMISSION", sequenceName = "SEQ_USER_ROL_PERMISSION", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_USER_ROL_PERMISSION")
	@Column(name = "ROL_PERMISSION_ID", unique = true, nullable = false, length = 10)
	public long getId() {
		return this.id;
	}

	public void setId(long rolPermissionId) {
		this.id = rolPermissionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROL_ID", nullable = false, foreignKey=@ForeignKey(name="T_USER_ROL_PERM_FK_ROL_ID"))
	public UserRol getUserRol() {
		return this.userRol;
	}

	public void setUserRol(UserRol userRol) {
		this.userRol = userRol;
	}

	@Column(name = "ROL_PERMISSION_NAME", nullable = false, length = 40)
	public String getRolPermissionName() {
		return this.rolPermissionName;
	}

	public void setRolPermissionName(String rolPermissionName) {
		this.rolPermissionName = rolPermissionName;
	}

	@Column(name = "ROL_PERMISSION_DESC")
	public String getRolPermissionDesc() {
		return this.rolPermissionDesc;
	}

	public void setRolPermissionDesc(String rolPermissionDesc) {
		this.rolPermissionDesc = rolPermissionDesc;
	}

	@Column(name = "ROL_PERMISSION_WEIGHT", nullable = false, precision = 22, scale = 0)
	public BigDecimal getRolPermissionWeight() {
		return this.rolPermissionWeight;
	}

	public void setRolPermissionWeight(BigDecimal rolPermissionWeight) {
		this.rolPermissionWeight = rolPermissionWeight;
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
