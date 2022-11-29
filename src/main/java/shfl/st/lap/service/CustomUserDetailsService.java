package shfl.st.lap.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shfl.st.lap.model.User;
import shfl.st.lap.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUserId(username);
		return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(),
				Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
	}
}
