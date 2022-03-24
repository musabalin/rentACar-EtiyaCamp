package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarDao extends JpaRepository<Car, Integer> {

    List<Car> getByModelYear(short modelYear);
    List<Car> getByModelYearIn(List<Short> modelYears);
    List<Car> getByModelYearAndDailyPrice(short modelYear,double dailyPrice);
    List<Car> getByDescriptionContains(String description);

}
