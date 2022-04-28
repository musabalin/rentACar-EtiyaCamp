package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.concretes.AdditionalServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalServiceOrderDao extends JpaRepository<AdditionalServiceOrder, Integer> {
    List<AdditionalServiceOrder> getByRentalId(int rentalId);
}
