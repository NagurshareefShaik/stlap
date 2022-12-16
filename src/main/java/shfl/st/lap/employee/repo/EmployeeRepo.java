package shfl.st.lap.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import shfl.st.lap.employee.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, String> {

	Employee findByEmployeeId(String employeeId);

}
