package com.cakefactory.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private Integer age;
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<UserAuthority> authorities = new HashSet<>();

    public void addAddress(Address address){
        addresses.add(address);
        address.setUser(this);
    }

    public void addAuthority(UserAuthority userAuthority){
        authorities.add(userAuthority);
        userAuthority.setUser(this);
    }
}
