package com.rock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rock.dao.UserDetailsDAO;
import com.rock.model.UserDetails;
/**
 * 
 * @author Ragland
 *
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsDAO employeeDAO;

	@Transactional
	public void addUserDetails(UserDetails userDetails) {
		employeeDAO.addUserDetails(userDetails);
	}

	@Transactional
	public List<UserDetails> getAllUserDetails() {
		return employeeDAO.getAllUserDetails();
	}

}
