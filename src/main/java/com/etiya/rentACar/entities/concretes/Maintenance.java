package com.etiya.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "maintenances")
public class Maintenance {
    @Id
    @Column(name = "maintenanceId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maintenanceId;

    @Column(name = "dateAdded")
    private LocalDate dateAdded;

    @Column(name = "dateReturned")
    private LocalDate dateReturned;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;



}
