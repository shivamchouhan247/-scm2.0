package com.scm.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {
    @Id
    private String contactId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition="TEXT")
    private String picture;
    private String websiteLink;
    private String LinkedinLink;
    private boolean favorite=false;

    @ManyToOne()
    private User user;

    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval = true)
    private List<SocialLink> socialLinks=new ArrayList<>();


}
