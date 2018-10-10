package com.twitter.demo.modal;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_name", nullable = false)
    private String userName;


    @OneToOne(cascade = CascadeType.ALL ,
            fetch = FetchType.LAZY ,
            mappedBy = "user")
    private UserProfile userProfile;

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
         this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public User()
    {

    }

    public User(String userName, String Password) {
        this.userName = userName;
        this.password = Password;
    }
}
