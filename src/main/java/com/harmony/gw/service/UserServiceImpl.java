package com.harmony.gw.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harmony.gw.entity.Employee;
import com.harmony.gw.entity.EmployeeRepository;

@Service
@Transactional
public class UserServiceImpl {

	@Autowired
    private EmployeeRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employee saveUser(Employee user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        return userRepository.save(user);
    }
    
    public Optional<Employee> findByUsername(String userId) {
        return userRepository.findByUserId(userId);
    }
    
}
