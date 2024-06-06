package com.scm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String userId;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Column(length = 2000)
    private String about;
    @Column(length = 2000)
    private String profilePic;
    private String phoneNumber;
    private String gender;
    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;
    private Provider provide = Provider.SELF;
    private String providerUserId;
    private Date createdDate;
    private Date updatedDate;
    private Date loginTime;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts= new ArrayList<>();



}
