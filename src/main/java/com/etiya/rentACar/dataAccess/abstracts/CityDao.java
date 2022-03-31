package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends JpaRepository<City, Integer> {

}
