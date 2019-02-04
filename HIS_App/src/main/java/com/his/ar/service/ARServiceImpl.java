package com.his.ar.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.his.ar.dao.ARUserMasterDao;
import com.his.ar.entity.ARUserMaster;
import com.his.ar.model.UserMaster;
import com.his.util.AppConstants;
import com.his.util.EmailService;
import com.his.util.PasswordService;

/**
 * This class is used to handle business operations is Usermanagement module
 * 
 * @author Ashok
 *
 */
@Service("arService")
public class ARServiceImpl implements ARService {

	Logger logger = LoggerFactory.getLogger(ARServiceImpl.class);

	@Autowired(required = true)
	private ARUserMasterDao arUserMasterDao;

	@Autowired(required = true)
	private EmailService emailService;

	/**
	 * This method is used to insert user record
	 */
	@Override
	public UserMaster saveUser(UserMaster um) {
		logger.debug("saveUser method started");
		ARUserMaster entity = new ARUserMaster();

		// Defaulting case worker as Active
		um.setActiveSw(AppConstants.STR_Y);
		um.setCreatedBy(AppConstants.ADMIN);

		// copying data from model to entity
		BeanUtils.copyProperties(um, entity);

		// Encrypting User Password
		String encryptedPwd = PasswordService.encrypt(um.getPwd());
		entity.setPwd(encryptedPwd);

		// Calling Repository method
		ARUserMaster savedEntity = arUserMasterDao.save(entity);

		logger.debug("User saved successfully");

		// Sending Email with Pwd
		if (savedEntity != null) {
			String text;
			try {
				text = getRegEmailBody(um);
				emailService.sendEmail(um.getEmail(), AppConstants.EMAIL_FROM, AppConstants.EMAIL_SUBJECT, text);
				logger.debug("Registration email sent successfully");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// setting generated pk value to model
		um.setUserId(savedEntity.getUserId());
		logger.debug("saveUser completed");
		return um;
	}

	/**
	 * This method is used to retrieve all case workers
	 */
	@Override
	public List<UserMaster> findAllUsers() {
		List<UserMaster> users = new ArrayList<UserMaster>();
		List<ARUserMaster> entities = arUserMasterDao.findAll();
		for (ARUserMaster entity : entities) {
			UserMaster master = new UserMaster();
			BeanUtils.copyProperties(entity, master);
			users.add(master);
		}
		return users;
	}

	/**
	 * This method is used to retrieve all caseworkers using pagination
	 */
	@Override
	public Page<ARUserMaster> findAllUsers(int pageNo, int pageSize) {
		Pageable pageble = new PageRequest(pageNo, pageSize);
		List<UserMaster> users = new ArrayList<UserMaster>();
		Page<ARUserMaster> pages = arUserMasterDao.findAll(pageble);
		return pages;
	}

	/**
	 * This method is used to fetch CW record using userid
	 */
	@Override
	public UserMaster findById(Integer userId) {
		ARUserMaster entity = arUserMasterDao.findById(userId).get();
		UserMaster model = new UserMaster();
		BeanUtils.copyProperties(entity, model);
		return model;
	}

	/**
	 * This method is used to update CaseWorker record
	 */
	@Override
	public UserMaster update(UserMaster userMaster) {
		ARUserMaster entity = new ARUserMaster();
		BeanUtils.copyProperties(userMaster, entity);

		// TODO:Need to fix this issue
		entity.setDob("");

		arUserMasterDao.save(entity);
		return userMaster;
	}

	/**
	 * 
	 */
	@Override
	public UserMaster findActiveUserByEmailAndPwd(String email, String pwd, String activeSw) {
		UserMaster um = new UserMaster();
		String encryptedPwd = PasswordService.encrypt(pwd);
		ARUserMaster arUserMaster = arUserMasterDao.findActiveUserByEmailAndPwd(email, encryptedPwd, "Y");
		BeanUtils.copyProperties(arUserMaster, um);
		return um;
	}

	/**
	 * This method is used to read Registration success email body content
	 * 
	 * @param um
	 * @return String
	 * @throws Exception
	 */
	private String getRegEmailBody(UserMaster um) throws Exception {
		String fileName = "Registration_Email_Template.txt";
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		StringBuilder mailBody = new StringBuilder("");
		while (line != null) {

			// Processing mail body content
			if (line.contains("USER_NAME")) {
				line = line.replace("USER_NAME", um.getFirstName() + " " + um.getLastName());
			}

			if (line.contains("APP_USER_EMAIL")) {
				line = line.replace("APP_USER_EMAIL", um.getEmail());
			}

			if (line.contains("APP_URL")) {
				line = line.replace("APP_URL", "<a href='http://localhost:9090/HIS/loginForm'>RI HIS</a>");
			}

			if (line.contains("APP_USER_PWD")) {
				line = line.replace("APP_USER_PWD", um.getPwd());
			}

			// Appending processed line to StringBuilder
			mailBody.append(line);

			// reading next line
			line = br.readLine();
		}

		fr.close();
		br.close();

		// Returning mail body content
		return mailBody.toString();
	}

	/**
	 * This method is used to find email existence in DB
	 */
	@Override
	public String findByEmail(String email) {
		logger.debug("finding email existence started");
		Integer cnt = arUserMasterDao.findByEmail(email);
		return (cnt >= 1) ? AppConstants.DUPLICATE : AppConstants.UNIQUE;
	}

}
