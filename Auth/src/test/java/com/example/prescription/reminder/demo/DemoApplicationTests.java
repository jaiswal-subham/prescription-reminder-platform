package com.example.prescription.reminder.demo;

import com.example.prescription.reminder.demo.controller.AuthController;
import com.example.prescription.reminder.demo.dao.LoginRequest;
import com.example.prescription.reminder.demo.dao.LoginResponse;
import com.example.prescription.reminder.demo.service.UserService;
import com.example.prescription.reminder.demo.utilities.ConstantsValues;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

@AutoConfigureMockMvc
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	AuthController authController;

	@MockBean
	UserService userService;

	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

//	@PostMapping(ConstantsValues.loginRoute)
//	ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){
//		String login = this.userService.login(loginRequest);
//		if(login.equals(ConstantsValues.loginFailed)) return new ResponseEntity<LoginResponse>(new LoginResponse(login), HttpStatus.EXPECTATION_FAILED);
//		return new ResponseEntity<LoginResponse>(new LoginResponse(login), HttpStatus.OK);
//	}

}

