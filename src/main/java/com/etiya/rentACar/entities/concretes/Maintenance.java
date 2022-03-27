package com.etiya.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "dateAdded")
    private String dateAdded;

    @Column(name = "dateReturned")
    private String dateReturned;

    @Column(name="durum")
    private Durum durum;


}
