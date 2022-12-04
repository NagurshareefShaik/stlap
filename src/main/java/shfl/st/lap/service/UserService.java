package shfl.st.lap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import shfl.st.lap.model.BranchMaster;
import shfl.st.lap.model.EmployeeMaster;
import shfl.st.lap.model.MenuMaster;
import shfl.st.lap.model.Role;
import shfl.st.lap.model.SubMenu;
import shfl.st.lap.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
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

	public String insertUser() {
		Role role=new Role();
		role.setRoleId(1);
		role.setRoleName("BA");
		
		
		List<MenuMaster> menuMasterList=new ArrayList<>();
		List<SubMenu> subMenuList=new ArrayList<>();
		List<SubMenu> subMenuList1=new ArrayList<>();
		
		MenuMaster menuMaster=new MenuMaster();
		menuMaster.setMenuId(1);
		menuMaster.setMenuName("voucher");
		menuMaster.setSubMenus(subMenuList);
		menuMaster.setRole(role);
		
		
		SubMenu subMenu=new SubMenu();
		subMenu.setId(1);
		subMenu.setSubMenuName("submenu");
		subMenu.setMenuList(menuMaster);
		
		SubMenu subMenu1=new SubMenu();
		subMenu1.setId(2);
		subMenu1.setSubMenuName("submenu1");
		subMenu1.setMenuList(menuMaster);
		
		subMenuList.add(subMenu);
		subMenuList.add(subMenu1);
		
		
		
		MenuMaster menuMaster1=new MenuMaster();
		menuMaster1.setMenuId(2);
		menuMaster1.setMenuName("dashboard");
		menuMaster1.setSubMenus(subMenuList1);
		menuMaster1.setRole(role);
		
		menuMasterList.add(menuMaster);
		menuMasterList.add(menuMaster1);
		role.setMenuList(menuMasterList);
		
		
		
		BranchMaster branchMaster=new BranchMaster();
		branchMaster.setBranchId(1);
		branchMaster.setBranchName("TAMBARAM");
		
		EmployeeMaster employeeMaster=new EmployeeMaster();
		employeeMaster.setBranch(branchMaster);
		employeeMaster.setCurrenltyLoggedIn(1);
		employeeMaster.setEmail("test");
		employeeMaster.setEmployeeId("ShareefSk");
		employeeMaster.setEmployeeName("Nagurshareef");
		employeeMaster.setIsActive(1);
		employeeMaster.setLastLoginTime(null);
		employeeMaster.setMobileNumber("123456");
		employeeMaster.setPassword("$2a$11$uKphF9LI8KGQ9DPdovf.N..DNYCiwczt.voGNloZvEesBota9e5Yi");
		employeeMaster.setRole(role);
		
		userRepo.save(employeeMaster);
		return null;
	}

}
