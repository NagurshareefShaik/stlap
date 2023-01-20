package shfl.st.lap.debittype.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shfl.st.lap.debittype.model.DebitType;

@Repository
public interface DebitTypeRepo extends JpaRepository<DebitType, Integer>{

}
