package shfl.st.lap.batch.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import shfl.st.lap.batch.entity.Customer;

public interface CustomerRepo  extends JpaRepository<Customer,Integer> {
}
