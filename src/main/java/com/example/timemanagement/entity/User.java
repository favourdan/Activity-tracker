package com.example.timemanagement.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table (name = "Time_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String email;
    private String password;
}
