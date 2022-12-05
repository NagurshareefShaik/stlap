package shfl.st.lap.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import shfl.st.lap.model.BranchMaster;
import shfl.st.lap.model.EmployeeMaster;
import shfl.st.lap.model.MenuMaster;
import shfl.st.lap.model.MobileUser;
import shfl.st.lap.model.Role;
import shfl.st.lap.model.SubMenu;
import shfl.st.lap.repo.MobileRepo;
import shfl.st.lap.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
	MobileRepo mobileRepo;
	
	public String registerUser(EmployeeMaster user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String msg="";
        EmployeeMaster employeeData=userRepo.save(user);
        if(Objects.nonNull(employeeData)){
            msg="Registration successfull";
        }else {
        	msg="Registration Not Successfull";
        }
        return msg;
    }

	public String registerMobileUser(MobileUser mobileUser) {
        String msg="";
        MobileUser mobileUser1=mobileRepo.save(mobileUser);
        if(Objects.nonNull(mobileUser1)){
            msg="Registration successfull";
        }else {
        	msg="Registration Not Successfull";
        }
        return msg;
	}

}
