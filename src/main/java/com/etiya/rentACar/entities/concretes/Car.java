package com.etiya.rentACar.entities.concretes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carId")
    private int id;

    @Column(name = "dailyPrice")
    private double dailyPrice;

    @Column(name = "description")
    private String description;

    @Column(name = "modelYear")
    private short modelYear;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "car")
    List<Damage> damages;

    @OneToMany(mappedBy = "car")
    List<Maintenance> maintenances;

    @Column(name="status_id")
    private CarState status;


}
