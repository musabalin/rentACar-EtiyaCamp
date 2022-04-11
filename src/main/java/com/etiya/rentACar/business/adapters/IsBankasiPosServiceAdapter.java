package com.etiya.rentACar.business.adapters;

import com.etiya.rentACar.core.utilities.services.posServices.IsBankasiPosService;
import com.etiya.rentACar.entities.concretes.CreditCard;
import org.springframework.stereotype.Service;

@Service
public class IsBankasiPosServiceAdapter implements PosService {

    IsBankasiPosService isBankasiPosService = new IsBankasiPosService();

    @Override
    public boolean makePayment(CreditCard creditCard) {


        return isBankasiPosService.makePayment
                (creditCard.getCreditCardNumber(), creditCard.getExpirationDate(), creditCard.getCvv());

    }
}
