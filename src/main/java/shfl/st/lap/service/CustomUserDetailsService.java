package shfl.st.lap.service;

import java.util.Collections;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shfl.st.lap.model.EmployeeMaster;
import shfl.st.lap.model.MenuMaster;
import shfl.st.lap.model.MobileUser;
import shfl.st.lap.repo.MobileRepo;
import shfl.st.lap.repo.UserRepo;
import shfl.st.lap.util.MobileNumberChecker;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserRepo repository;
	
	@Autowired
	private MobileRepo mobileRepo;
	
	@Autowired
	private MobileNumberChecker checker;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(checker.check(username)) {
			MobileUser mobileUser = mobileRepo.findByMobileNumber(username);
			return new org.springframework.security.core.userdetails.User(mobileUser.getMobileNumber(), mobileUser.getOtp(),
					Collections.singleton(new SimpleGrantedAuthority(mobileUser.getRole())));
		}
		
		EmployeeMaster employeeMaster = repository.findByEmployeeId(username);
//		Set<MenuMaster> data=employeeMaster.getRole().getMenuList();
//		data.stream().forEach(menu->{
//			System.out.println(menu.getMenuName());
//			menu.getSubMenus().stream().forEach(subMenu->{
//				System.out.println(subMenu.getSubMenuName());
//			});
//		});
		return new org.springframework.security.core.userdetails.User(employeeMaster.getEmployeeId(), employeeMaster.getPassword(),
				Collections.singleton(new SimpleGrantedAuthority(employeeMaster.getRole())));
	}
}
