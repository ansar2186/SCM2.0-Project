package com.scm.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_social_link")
public class SocialLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String link;
    private String title;
    @ManyToOne
    private Contact contact;
}
