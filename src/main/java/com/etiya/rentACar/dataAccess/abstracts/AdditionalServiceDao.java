package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.business.responses.additionalService.AdditionalServiceDto;
import com.etiya.rentACar.entities.concretes.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalServiceDao extends JpaRepository<AdditionalService,Integer> {
    AdditionalService getById(int id);
}
