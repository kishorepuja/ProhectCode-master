package com.his.ar.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.ar.entity.ARUserMaster;
import com.his.ar.model.UserMaster;
import com.his.ar.service.ARService;
import com.his.util.ARConstants;

/**
 * This class is used to Handle UserManagerment requests in this application
 * 
 * @author Ashok
 *
 */
@Controller
public class ARController {

	private static final Logger logger = LoggerFactory.getLogger(ARController.class);

	@Autowired(required = true)
	private ARService arService;

	/**
	 * This method is written to display CaseWorker Registration form
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/regUserForm", method = RequestMethod.GET)
	public String regUserForm(Model model) {
		logger.debug("Started CaseWorker Registration Form Display");
		UserMaster um = new UserMaster();
		model.addAttribute("um", um);
		initForm(model);
		logger.debug("Ended CaseWorker Registration Form Display");
		logger.info("Registation form loading successfull");
		return "userReg";
	}

	/**
	 * This method is written to display login form
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String index(Model model) {
		UserMaster um = new UserMaster();
		model.addAttribute("um", um);
		return "index";
	}

	/**
	 * This method is used to initialize CaseWorker Registration form
	 * 
	 * @param model
	 */
	private void initForm(Model model) {
		List<String> rolesList = new ArrayList();
		rolesList.add("Admin");
		rolesList.add("Case Worker");
		model.addAttribute("rolesList", rolesList);
		logger.debug("Registration form values are intialized to model scope");
	}

	/**
	 * This method is used to save CaseWorker profile
	 * 
	 * @param um
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/regUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("um") UserMaster um, Model model) {
		logger.debug("Caseworker registration started");
		// call service layer
		UserMaster master = arService.saveUser(um);

		if (master.getUserId() != null) {
			// store success msg
			model.addAttribute(ARConstants.SUCCESS, ARConstants.REG_SUCCESS);
		} else {
			// store error msg
			model.addAttribute(ARConstants.ERROR, ARConstants.REG_ERROR);
		}
		initForm(model);
		logger.debug("CaseWorker registration completed");
		logger.info("CaseWorker registation successfull");
		return "userReg";
	}

	/**
	 * This method is used to check case worker login functionality
	 * @param um
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginCheck(@ModelAttribute("um") UserMaster um, Model model) {
		String view = "";
		// call service layer
		UserMaster master = arService.findActiveUserByEmailAndPwd(um.getEmail(), um.getPwd(), "Y");
		if (master != null) {
			// Valid User
			view = "dashboard";
		} else {
			// In Valid User
			view = "index";
			model.addAttribute(ARConstants.ERROR, ARConstants.INVALID_USER);
		}
		return view;
	}

	/**
	 * This method is used to retrieve all case workers
	 */
	@RequestMapping(value = "/viewCaseWorkers")
	public String viewAllCaseWorkers(@RequestParam("cpn") String pageNo, Model model) {

		Integer currentPageNo = 1;
		List<UserMaster> users = new ArrayList();

		if (null != pageNo && !"".equals(pageNo)) {
			currentPageNo = Integer.parseInt(pageNo);
		}

		Page<ARUserMaster> page = arService.findAllUsers(currentPageNo - 1, ARConstants.PAGE_SIZE);
		int totalPages = page.getTotalPages();
		List<ARUserMaster> entities = page.getContent();

		for (ARUserMaster entity : entities) {
			UserMaster um = new UserMaster();
			BeanUtils.copyProperties(entity, um);
			users.add(um);
		}

		model.addAttribute("cpn", pageNo);
		model.addAttribute("tp", totalPages);
		model.addAttribute("caseWorkers", users);
		return "viewCaseWorkers";
	}

	/**
	 * This method is used to check unique email
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/regUserForm/checkEmail")
	public @ResponseBody String checkUniqueEmail(@RequestParam(name = "email") String email) {
		System.out.println("EMail entered : " + email);
		return arService.findByEmail(email);
	}
}
