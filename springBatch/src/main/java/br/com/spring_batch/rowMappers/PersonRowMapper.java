package br.com.spring_batch.rowMappers;

import br.com.spring_batch.entities.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setAccount_id(rs.getLong("account_id"));
        person.setName(rs.getString("name"));
        person.setPerson_id(rs.getLong("person_id"));

        return person;
    }
}
