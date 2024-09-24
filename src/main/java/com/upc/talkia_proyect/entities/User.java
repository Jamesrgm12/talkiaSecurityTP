package com.upc.talkia_proyect.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('users_id_seq')")
    @Column(name = "users_id", nullable = false)
    private Integer id;

    @Column(name = "user_name", length = 50, unique = true)
    private String userName;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "total_points")
    private Double totalPoints;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference("user_roles")
    private Set<Role> roles = new HashSet<>();

    /*
    @ManyToOne
    @JoinColumn(name = "roles_id", nullable = false)
    @JsonManagedReference("user_roles")
    private Role role;
     */
    @Column(name = "i_created_at",updatable = false)
    private LocalDateTime iCreatedAt;

    @Column(name = "i_modified_at")
    private LocalDateTime iModifiedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "levels_id")
    private Level level;

    @PrePersist
    protected void onCreate() {
        iCreatedAt = LocalDateTime.now();
        iModifiedAt = LocalDateTime.now();

    }
    @PreUpdate
    protected void onUpdate() {
        iModifiedAt = LocalDateTime.now();
    }
}