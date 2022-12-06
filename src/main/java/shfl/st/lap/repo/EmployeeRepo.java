package shfl.st.lap.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import shfl.st.lap.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, String> {

	Employee findByEmployeeId(String employeeId);

}
