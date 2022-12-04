package shfl.st.lap.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import shfl.st.lap.model.MobileUser;

public interface MobileRepo extends JpaRepository<MobileUser, String> {

	MobileUser findByMobileNumber(String mobileId);

}
