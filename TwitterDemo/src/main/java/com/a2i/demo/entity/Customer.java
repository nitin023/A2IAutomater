package com.a2i.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String email;
    private String phoneNumber;

}
