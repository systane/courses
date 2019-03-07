package br.com.spring_batch.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Person {
    @Id
    private Long person_id;
    private Long account_id;
    private String name;
}
