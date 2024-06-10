package com.scm.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_social_link")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String link;
    private String title;
    @ManyToOne
    private Contact contact;
}
