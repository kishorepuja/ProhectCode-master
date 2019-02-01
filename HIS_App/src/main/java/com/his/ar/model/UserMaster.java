package com.his.ar.model;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

/**
 * This class is used to bind User Forms data
 * 
 * @author Hello
 *
 */
@Data
public class UserMaster {

	/**
	 * This is userId
	 */
	private Integer userId;

	/**
	 * This is FirstName
	 */
	private String firstName;
	
	/**
	 */

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
