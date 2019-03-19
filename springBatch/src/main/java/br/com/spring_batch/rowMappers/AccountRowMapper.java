package br.com.spring_batch.rowMappers;

import br.com.spring_batch.entities.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {

        Account account = new Account();
        account.setAccount_id(rs.getLong("account_id"));
        account.setUsername(rs.getString("username"));
        account.setEmail(rs.getString("email"));
        account.setPassword(rs.getString("password"));

        return account;
    }
}
