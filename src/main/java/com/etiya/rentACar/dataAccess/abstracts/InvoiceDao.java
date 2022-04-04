package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceDao extends JpaRepository<Invoice, Integer> {

    List<Invoice> getByCustomerId(int customerId);

    List<Invoice> findByCreateDateBetween(LocalDate startDate, LocalDate endDate);
}
