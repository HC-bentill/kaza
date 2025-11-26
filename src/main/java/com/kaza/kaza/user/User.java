package com.kaza.kaza.user;

import jakarta.persistence.*;
import lombok.*;            // <-- Lombok imports
import java.time.LocalDateTime;
import java.util.UUID;

@Data                   // generates getters, setters, toString, equals, hashCode
@Builder                // enables builder pattern
@NoArgsConstructor      // generates no-arg constructor
@AllArgsConstructor     // generates all-args constructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String fullName;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;
}
