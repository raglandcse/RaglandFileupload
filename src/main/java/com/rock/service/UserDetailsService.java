package com.rock.service;

import java.util.List;

import com.rock.model.UserDetails;
/**
 * 
 * @author Ragland
 *
 */
public interface UserDetailsService {

	public void addUserDetails(UserDetails userDetails);

	public List<UserDetails> getAllUserDetails();

}
