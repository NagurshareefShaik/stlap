package shfl.st.lap.sanctionlist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shfl.st.lap.sanctionlist.model.SanctionList;

@Repository
public interface SanctionRepo extends JpaRepository<SanctionList, String>{
	

}
