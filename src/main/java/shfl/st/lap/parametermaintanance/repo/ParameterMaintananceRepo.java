package shfl.st.lap.parametermaintanance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shfl.st.lap.parametermaintanance.model.ParameterMaintanance;

@Repository
public interface ParameterMaintananceRepo extends JpaRepository<ParameterMaintanance, Integer>{

}
