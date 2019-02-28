package br.com.spring_batch.repositories;

import br.com.spring_batch.entities.Account;
import br.com.spring_batch.rowMappers.AccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;

@Repository("accountRepository")
public class AccountRepository {

    private final DataSource dataSource;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String EMAIL = "%@hotmail.com";
    private static final String PERSON_ID = "id";

    @Autowired
    public AccountRepository(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Account readAccount(){
        String query = "Select * from Account where email like :email ";

        return jdbcTemplate.queryForObject(query, new MapSqlParameterSource("email", EMAIL), new AccountRowMapper());
    }

    public Account alterAccount(Long id, String email){
        String query = "update account set email = :email where person_id = :id";
        jdbcTemplate.update(query, new MapSqlParameterSource("email", email).addValue(PERSON_ID, id));

        String querySelect = "Select * from Account where person_id = :id ";
        return jdbcTemplate.queryForObject(querySelect, new MapSqlParameterSource("id", id), new AccountRowMapper());
    }
}
