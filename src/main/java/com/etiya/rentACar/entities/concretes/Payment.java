package com.etiya.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    /*
        @OneToMany
        private List<AdditionalServiceOrder> additionalServiceOrder;
    */
    @Column(name = "totalPrice")
    private double totalPrice;


}
