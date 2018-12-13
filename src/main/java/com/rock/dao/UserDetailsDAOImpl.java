package com.rock.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rock.model.UserDetails;
/**
 * 
 * @author Ragland
 *
 */
@Repository
public class UserDetailsDAOImpl implements UserDetailsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addUserDetails(UserDetails userDetails) {
		sessionFactory.getCurrentSession().saveOrUpdate(userDetails);

	}

	@SuppressWarnings("unchecked")
	public List<UserDetails> getAllUserDetails() {

		return sessionFactory.getCurrentSession().createQuery("from UserDetails").list();
	}

}