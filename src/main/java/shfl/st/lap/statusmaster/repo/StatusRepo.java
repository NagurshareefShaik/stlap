package shfl.st.lap.statusmaster.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shfl.st.lap.statusmaster.model.Status;

@Repository
public interface StatusRepo extends JpaRepository<Status, Integer>{

}
