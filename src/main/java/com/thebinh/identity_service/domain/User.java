package com.thebinh.identity_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 3, message = "username phải có tối thiểu 3 kí tự")
    private String username;
    @Size(min = 6, message = "password phải có tối thiểu 6 kí tự")
    private String password;
    private String firstName;
    private String lastName;
    private String dob;

}
