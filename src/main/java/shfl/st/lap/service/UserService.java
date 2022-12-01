package shfl.st.lap.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import shfl.st.lap.model.User;
import shfl.st.lap.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	public String registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String msg="";
        User userData=userRepo.save(user);
        if(Objects.nonNull(userData)){
            msg="Registration successfull";
        }
        return msg;
    }

}
