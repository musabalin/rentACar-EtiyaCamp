package com.etiya.rentACar.business.adapters;

import com.etiya.rentACar.entities.concretes.CreditCard;

public interface PosService {

    boolean makePayment(CreditCard creditCard);
}
