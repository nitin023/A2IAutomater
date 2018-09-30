package com.twitter.demo.modal;


import javax.persistence.*;

@Entity
@Table(name = "user_entity")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    @Column(name="userid", nullable=false)
    private Long userId;
    @Column(name="firstname", nullable=false)
    private String firstName;
    @Column(name="middlename")
    private String middleName;
    @Column(name="lastname", nullable=false)
    private String lastName;
    @Column(name="username", nullable=false)
    private String userName;
    @Column(name="emailid", nullable=false)
    private String emailId;
    @Column(name="password", nullable=false)
    private String password;
    @Column(name="mobile", nullable=false)
    private String mobile;
    @Column(name="address", nullable=false)
    private String address;

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public User(String firstName, String middleName, String lastName,
                String userName, String emailId, String password,
                String mobile, String address) {


        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.mobile = mobile;
        this.address = address;
    }
}
