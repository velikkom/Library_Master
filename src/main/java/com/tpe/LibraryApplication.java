package com.tpe;

import com.tpe.entity.enums.RoleType;
import com.tpe.entity.user.Role;
import com.tpe.entity.user.User;
import com.tpe.payload.request.user.SaveUserRequest;
import com.tpe.repository.user.UserRepository;
import com.tpe.repository.user.UserRoleRepository;
import com.tpe.service.user.UserRoleService;
import com.tpe.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	private final UserRoleService userRoleService;
	private final UserRoleRepository userRoleRepository;
	private final UserService userService;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public LibraryApplication(UserRoleService userRoleService, UserRoleRepository userRoleRepository, UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRoleService = userRoleService;
		this.userRoleRepository = userRoleRepository;
		this.userService = userService;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Role tablosunu oluşturuyoruz
		if (userRoleService.getAllUserRole().isEmpty()) {

			Role admin = new Role();
			admin.setRoleType(RoleType.ROLE_ADMIN);
			admin.setRoleName("Admin");
			userRoleRepository.save(admin);

			Role member = new Role();
			member.setRoleType(RoleType.ROLE_MEMBER);
			member.setRoleName("Member");
			userRoleRepository.save(member);

			Role employee = new Role();
			employee.setRoleType(RoleType.ROLE_EMPLOYEE);
			employee.setRoleName("Employee");
			userRoleRepository.save(employee);
		}

		// Built-in admin yapıyoruz
		if (userService.countAllAdmins() == 0) {

			SaveUserRequest adminRequest = new SaveUserRequest();
			adminRequest.setEmail("aaa@bbb.com");
			adminRequest.setPassword("12345678");
			adminRequest.setFirstName("Ayşe");
			adminRequest.setLastName("Nihal");
			adminRequest.setAddress("Ankara,Turkey");
			adminRequest.setPhone("999-999-9999");
			adminRequest.setBirthDate(LocalDate.of(1994, 5, 22));
			adminRequest.setRole("ADMIN");

			userService.saveAdmin(adminRequest);
		}

		/*if (userRepository.countAllAdmins() == 0) {
			User adminUser = new User();
			adminUser.setEmail("admin@example.com");
			adminUser.setPassword(passwordEncoder.encode("adminpassword"));
			adminUser.setRoles(Set.of(userRoleRepository.findByRoleType(RoleType.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Role not found"))));
			adminUser.setFirstName("Admin");
			adminUser.setLastName("User");
			adminUser.setAddress("Admin Address");
			adminUser.setPhone("123-456-7890");
			adminUser.setCreateDate(LocalDateTime.now());
			userRepository.save(adminUser);
		}*/
	}
}
