package com.his.ar.model;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

@Data
public class UserMaster {

	private Integer userId;

	private String firstName;

	private String lastName;

	private String email;

	private String pwd;

	private String dob;

	private String phno;

	private String activeSw;

	private String userRole;

	private Timestamp createdDate;

	private Timestamp updatedDate;

	private String createdBy;

	private String updatedBy;

}
