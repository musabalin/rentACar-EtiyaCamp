package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.concretes.Durum;
import com.etiya.rentACar.entities.concretes.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceDao extends JpaRepository<Maintenance, Integer> {

    List<Maintenance> getByCarId(int id);

    Boolean existsMaintenanceByCarId (int carId);

    List<Maintenance> getByMaintenanceId(int id);


}
