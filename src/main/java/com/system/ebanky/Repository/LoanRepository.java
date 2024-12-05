package com.system.ebanky.Repository;

import com.system.ebanky.Entity.Loan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends GenericRepository<Loan>{
    List<Loan> findByUserId(long id);
}
