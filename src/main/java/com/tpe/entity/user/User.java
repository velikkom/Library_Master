package com.tpe.entity.user;

import com.tpe.entity.business.Loan;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String firstName;

    @Column(nullable = false, length = 30)
    private String lastName;

    @Column(nullable = false)
    private int score = 0;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 12)
    private String phone;

    private LocalDate birthDate;

    @Column(nullable = false, length = 80)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime createDate;

    private String resetPasswordCode;

    @Column(nullable = false)
    private boolean builtIn = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "userRoles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<Loan> loans;



}
