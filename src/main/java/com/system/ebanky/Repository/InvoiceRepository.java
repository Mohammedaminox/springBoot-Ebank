package com.system.ebanky.Repository;

import com.system.ebanky.Entity.Invoice;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepository extends GenericRepository<Invoice>{
    List<Invoice> findByUserId(long id);
    List<Invoice> findByDueDateBeforeAndAmountDueGreaterThan(LocalDate dueDate, Double paidAmount);
}
