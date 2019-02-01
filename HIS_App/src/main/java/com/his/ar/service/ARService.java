package com.his.ar.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.his.ar.entity.ARUserMaster;
import com.his.ar.model.UserMaster;

/**
 * This interface is used to define user management service layer method
 * @author Hello
 *
 */
public interface ARService {

	public UserMaster saveUser(UserMaster userMaster);

	public List<UserMaster> findAllUsers();

	public Page<ARUserMaster> findAllUsers(int pageNo,int pageSize);

	public UserMaster findById(Integer userId);

	// updating and deleting
	public UserMaster update(UserMaster userMaster);

	public UserMaster findActiveUserByEmailAndPwd(String email, String pwd, String activeSw);

	
	public String findByEmail(String email);
	
}
