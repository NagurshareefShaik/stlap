package shfl.st.lap.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import shfl.st.lap.model.EmployeeMaster;

public interface UserRepo extends JpaRepository<EmployeeMaster, String> {

	EmployeeMaster findByEmployeeId(String employeeId);

}
