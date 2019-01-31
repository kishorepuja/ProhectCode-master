package com.his.ar.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "AR_USER_MASTER")
@Data
public class ARUserMaster {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "USER_EMAIL")
	private String email;

	@Column(name = "USER_PWD")
	private String pwd;

	@Column(name = "USER_DOB")
	private String dob;

	@Column(name = "USER_PHNO")
	private String phno;

	@Column(name = "ACTIVE_SW")
	private String activeSw;

	@Column(name = "USER_ROLE")
	private String userRole;

	@CreationTimestamp
	@Column(name = "CREATE_DT")
	private Timestamp createdDate;

	@UpdateTimestamp
	@Column(name = "UPDATE_DT")
	private Timestamp updatedDate;

	@Column(name = "CREATE_BY")
	private String createdBy;

	@Column(name = "UPDATE_BY")
	private String updatedBy;

}
