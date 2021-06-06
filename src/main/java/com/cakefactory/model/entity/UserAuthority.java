package com.cakefactory.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_authority")
@Data
public class UserAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //todo: change to enum.
    private String authority;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
