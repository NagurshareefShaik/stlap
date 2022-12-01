package shfl.st.lap.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import shfl.st.lap.model.User;

public interface UserRepo extends JpaRepository<User, String> {

	User findByEmployeeId(String userId);

}
