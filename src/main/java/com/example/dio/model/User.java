package com.example.dio.model;

import com.example.dio.enums.UserRole;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
@Entity
@Table(name="users")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column(name="user_id")
    private long userId;

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name="ph_no")
    private String phno;

    @CreatedDate
    @Column(name="created_at")
    private LocalDate createdAt;

    @LastModifiedDate
    @Column(name="last_modified_at")
    private LocalDate lastModifiedAt;


}
