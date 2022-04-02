package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.concretes.AdditionalServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalServiceOrderDao extends JpaRepository<AdditionalServiceOrder, Integer> {
}
