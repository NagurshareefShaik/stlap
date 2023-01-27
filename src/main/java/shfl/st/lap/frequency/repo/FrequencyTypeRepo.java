package shfl.st.lap.frequency.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shfl.st.lap.frequency.model.FrequencyType;

@Repository
public interface FrequencyTypeRepo extends JpaRepository<FrequencyType, Integer>{

}
