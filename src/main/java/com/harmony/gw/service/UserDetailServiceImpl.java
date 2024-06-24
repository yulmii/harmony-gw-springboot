package com.harmony.gw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.harmony.gw.entity.Employee;
import com.harmony.gw.entity.EmployeeRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private EmployeeRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Optional<Employee> user = repository.findByUserId(userId);
		UserBuilder builder = null;
		if (user.isPresent()) {
			Employee currentUser = user.get();
			builder = org.springframework.security.core.userdetails.User.withUsername(userId);
			builder.password(currentUser.getPassword());
			builder.roles(currentUser.getRole());
		} else {
			throw new UsernameNotFoundException("User not found");
		}
		return builder.build();
	}

}
