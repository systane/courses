package br.com.spring_batch.entities;


import lombok.Data;

@Data
public class Account {
    private Long person_id;
    private String username;
    private String password;
    private String email;
}
