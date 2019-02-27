package br.com.spring_batch.repositories;

import br.com.spring_batch.entities.Account;
import br.com.spring_batch.rowMappers.AccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class AccountRepository {

    @Autowired
    private DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String PERSON_ID = "listPersonId";

    @PostConstruct
    private  void postConstruct(){
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Account> readAccount(){
//        String query = "Select * from Account where person_id in (:listPersonId) ";
//        return jdbcTemplate.query(query, new MapSqlParameterSource(PERSON_ID, listId), new AccountRowMapper());
        String query = "Select * from Account";

        return jdbcTemplate.query(query, new AccountRowMapper());
    }
}
