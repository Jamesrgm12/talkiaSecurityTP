package com.upc.talkia_proyect.dtos;


import com.upc.talkia_proyect.entities.Level;
import com.upc.talkia_proyect.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String userName;
    private String name;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private Double totalPoints;
    private Set<Role> roles;
    private LocalDateTime iCreatedAt;
    private LocalDateTime iModifiedAt;
    private Level level;
}
