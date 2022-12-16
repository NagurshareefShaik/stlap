package shfl.st.lap.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import shfl.st.lap.employee.model.MobileUser;

public interface MobileRepo extends JpaRepository<MobileUser, String> {

	MobileUser findByMobileNumber(String mobileId);

}
