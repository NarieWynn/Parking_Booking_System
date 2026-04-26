package com.pbs.parking_booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

@Data
@Table("User") 
public class User {
    @Id
    private Integer userId; // INT trong SQL map với Integer

    private String email; // (UNIQUE)
    private String password;
    private String role;
    private String userStatus; // 'active'
    private String firstName;
    private String middleName;
    private String lastName;
}