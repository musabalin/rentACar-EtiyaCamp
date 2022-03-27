package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.concretes.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusDao extends JpaRepository<Status, Integer> {
}
