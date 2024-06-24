package com.harmony.gw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.harmony.gw.entity.Employee;
import com.harmony.gw.entity.EmployeeRepository;


@SpringBootApplication
public class HarmonyApplication implements CommandLineRunner {

	@Autowired
    private EmployeeRepository urepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HarmonyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// 사용자 이름: user, 암호: user
		urepository.save(new Employee("user", "$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue","USER"));
		urepository.save(new Employee("admin", "$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW", "ADMIN"));
	}
}
