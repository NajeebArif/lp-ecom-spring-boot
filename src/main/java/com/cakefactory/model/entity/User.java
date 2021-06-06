package com.cakefactory.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
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
    private String userName;
    private Integer age;
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Address> addresses;
}
