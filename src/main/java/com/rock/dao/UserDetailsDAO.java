package com.rock.dao;

import java.util.List;

import com.rock.model.UserDetails;
/**
 * 
 * @author Ragland
 *
 */
public interface UserDetailsDAO {

	public void addUserDetails(UserDetails userDetails);

	public List<UserDetails> getAllUserDetails();

}
