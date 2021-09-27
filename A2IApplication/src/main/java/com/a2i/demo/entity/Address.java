package com.a2i.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String city;
    private String state;
    private String pincode;
    private String streetAddress;
}
