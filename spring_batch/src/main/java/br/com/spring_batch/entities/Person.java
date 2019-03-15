package br.com.spring_batch.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "person_person_id_seq")
    private Long person_id;
    private Long account_id;
    private String name;
}
