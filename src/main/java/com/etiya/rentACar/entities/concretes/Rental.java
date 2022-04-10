package com.etiya.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "rentals")
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "dateAdded")
    private LocalDate dateAdded;

    @Column(name = "dateReturned")
    private LocalDate dateReturned;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "beforeRentalKilometer")
    private int beforeRentalKilometer;

    @Column(name = "afterRentalKilometer")
    private int afterRentalKilometer;

    @ManyToOne
    @JoinColumn(name = "rent_city_id")
    private City rentCityId;

    @ManyToOne
    @JoinColumn(name = "return_city_id")
    private City returnCityId;

    @Column(name = "dailyPrice")
    private double dailyPrice;

    @OneToMany(mappedBy = "rental")
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "rental")
    private List<AdditionalServiceOrder> additionalServiceOrders;

    @OneToOne(mappedBy = "rental")
    private  Payment payment;

}
