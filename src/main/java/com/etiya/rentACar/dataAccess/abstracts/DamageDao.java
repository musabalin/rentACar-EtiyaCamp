package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.concretes.Damage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DamageDao extends JpaRepository<Damage, Integer> {
    List<Damage> getByCarId(int id);
}
