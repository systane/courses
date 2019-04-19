package br.com.spring_batch.entities;


import lombok.Data;

import javax.persistence.*;

/**
 * This entity represents the Account table.
 */
@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "account_account_id_seq")
    private Long account_id;
    private String username;
    private String password;
    private String email;
}
