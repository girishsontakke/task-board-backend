package com.girishms.springbootdemo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.girishms.springbootdemo.constants.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String userName;

    private String firstName;

    private String lastName;

    @NotNull
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @JsonIgnore
    @NotNull
    private String password;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Task> tasks;

    private ZonedDateTime createAt;
    private ZonedDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;
}
