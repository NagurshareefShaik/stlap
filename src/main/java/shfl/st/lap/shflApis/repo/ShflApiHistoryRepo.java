package shfl.st.lap.shflApis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shfl.st.lap.shflApis.model.ShflApiHistory;

@Repository
public interface ShflApiHistoryRepo extends JpaRepository<ShflApiHistory, Integer> {

}
