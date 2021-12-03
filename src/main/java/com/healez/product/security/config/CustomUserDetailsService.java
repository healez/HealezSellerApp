package com.healez.product.security.config;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.healez.product.entity.UserDetailEntity;
import com.healez.product.repository.UserDetailRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private UserDetailRepo userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Optional<UserDetailEntity> user = userRepository.findById(username);

    		if(user.isPresent()) {
	            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    		}else {
	            return (UserDetails) user.get();
    		}
     }
}
