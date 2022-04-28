package com.etiya.rentACar.business.constants.messages;

public class BusinessMessages {


    public static class MaintenanceMessages {
        public static final String CAR_UNDERMAINTENANCE = "Araç Bakımda";
        public static final String CAR_AVAILABLE = "Araç Bakımdan Çıktı.";
    }

    public static class BrandMessages {
        public static final String BRAND_ADD = "BRAND_ADD";
        public static final String BRAND_DELETE = "Marka Silindi.";
        public static final String BRAND_UPDATE = "Marka Güncellendi.";
        public static final String BRAND_IS_ALREADY_EXISTS = "Bu Marka zaten var.";
        public static final String BRAND_DIDNT_ADD ="BRAND_DIDNT_ADD" ;
    }

    public static class InvoiceMessages {
        public static final String INVOICE_ADD = "Fatura Eklendi..";
    }
    public static class AdditionalServiceOrderMessages {
        public static final String ADDITIONAL_SERVICE_ORDER_ADD = "Ek Hizmet Siparişi Eklendi..";
        public static final String ADDITIONAL_SERVICE_ORDER_DELETE = "Ek Hizmet Siparişi Silindi..";
        public static final String ADDITIONAL_SERVICE_ORDER_UPDATE = "Ek Hizmet Siparişi Güncellendi..";
    }

    public static class RentalMessages {
        public static final String RENTAL_RENTED = "Araç kiralandı.";
    }

    public static class ColorMessages {

        public static final String COLOR_ADD = "Marka Eklendi.";
        public static final String COLOR_IS_ALREADY_EXISTS = "Bu Renk var.";
    }

    public static class PaymentMessages {
        public static final String PAYMENT_ADD = "Ödeme gerçekleşti";
    }

    public static class CityMessages {
        public static final String CITY_ADD = "Şehir eklendi.";
        public static final String CITY_DELETE = "Şehir Silindi.";
        public static final String CITY_UPDATE = "Şehir Güncellendi.";
    }

    public static class CarMessages {
        public static final String CAR_ADD = "Araç Eklendi..";
        public static final String CAR_REMOVE = "Araç Silindi..";
        public static final String CAR_UPDATE = "Araç Güncellendi..";
    }



}
