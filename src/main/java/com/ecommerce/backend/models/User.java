package com.ecommerce.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table (name = "users")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class User {

    public User(
            String name,
            String lastName,
            Integer phone,
            String email
    ){
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    @Id
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName="user_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    Integer id;

    @Column (nullable = false)
    String name;

    @Column (nullable = false)
    String lastName;

    @Column (nullable = false)
    Integer phone;

    @Column (nullable = false)
    String email;
}
