package com.fin_app.user_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AddressEntity {
    @Column( name ="address" , nullable = false)
    private String addressLine;

    @Column( name ="city" , nullable = false)
    private String city;

    @Column( name ="postal_code" , nullable = false)
    private int postalCode;

    @Column( name ="state" , nullable = false)
    private String state;
}
