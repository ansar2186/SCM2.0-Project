package com.scm.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tbl_user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    private String userId;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Getter(value = AccessLevel.NONE)
    private String password;
    @Column(length = 2000)
    private String about;
    @Column(length = 2000)
    private String profilePic;
    private String phoneNumber;
    private String gender;
    @Getter(value = AccessLevel.NONE)
    private boolean enabled = true;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;
    @Enumerated(value = EnumType.STRING)
    private Provider provider = Provider.SELF;
    private String providerUserId;
    private Date createdDate;
    private Date updatedDate;
    private Date loginTime;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts= new ArrayList<>();

    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> roleList= new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
