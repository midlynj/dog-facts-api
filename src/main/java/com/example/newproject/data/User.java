package com.example.newproject.data;

import com.example.newproject.Enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email
    @NotEmpty
    @Column(nullable = false, length = 124)
    private String email;


    @Column(nullable = false, length = 124)
    private String userName;

    @ToString.Exclude
    @Column(nullable = false, length = 124)
    private String password;

    @Column(nullable = false, length = 124)
    private LocalDate createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties("author")
    private Collection<Post> posts;
}
