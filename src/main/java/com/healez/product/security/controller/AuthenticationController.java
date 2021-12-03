package com.healez.product.security.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healez.product.entity.UserDetailEntity;
import com.healez.product.security.common.DeviceProvider;
import com.healez.product.security.common.TokenHelper;
import com.healez.product.security.model.UserTokenState;
import com.healez.product.security.security.JwtAuthenticationRequest;


@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {


	private static final Logger LOGGER = LogManager.getLogger(AuthenticationController.class.getName());

	
	@Autowired
	private TokenHelper tokenHelper;

	@Lazy
	@Autowired
	private AuthenticationManager authenticationManager;


	@Autowired
	private DeviceProvider deviceProvider;

	@Bean
	private  PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response, Device device) throws AuthenticationException, IOException {

		LOGGER.info(device.toString() + "authenticationRequest "  + authenticationRequest.getUsername());

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));

		
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// token creation
		UserDetailEntity user = (UserDetailEntity) authentication.getPrincipal();
		String jws = tokenHelper.generateToken(user.getUserName(), device);
		
		long expiresIn = tokenHelper.getExpiredIn(device);
		
		
		// Return the token
		return ResponseEntity.ok(new UserTokenState(jws, expiresIn));
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request, HttpServletResponse response,
			Principal principal) {

		String authToken = tokenHelper.getToken(request);
		
		LOGGER.info("refreshAuthenticationToken() " +  authToken);

		Device device = deviceProvider.getCurrentDevice(request);

		if (authToken != null && principal != null) {

			String refreshedToken = tokenHelper.refreshToken(authToken, device);
			long expiresIn = tokenHelper.getExpiredIn(device);

			return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.accepted().body(userTokenState);
		}
	}

	
	@RequestMapping(value = "/getEncryptedPassword", method = RequestMethod.GET)
	public ResponseEntity<?> getEncryptedPassword(@RequestParam String password, HttpServletRequest request,
			HttpServletResponse response) {
		return ResponseEntity.accepted().body(passwordEncoder().encode(password));
	}
	

}