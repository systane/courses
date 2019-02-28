package br.com.spring_batch.repositories;

import br.com.spring_batch.entities.Xugus;
import br.com.spring_batch.rowMappers.XugusRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository("xugusRepository")
public class XugusRepository {

    private final DataSource dataSource;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String NOME = "nome";
    private static final String PERSON_ID = "person_id";

    @Autowired
    public XugusRepository(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Xugus insert(Xugus xugus){
        String query = "insert into Xugus (person_id, nome) values (:person_id, :nome)";

        jdbcTemplate.update(query, new MapSqlParameterSource(PERSON_ID, xugus.getPerson_id())
                .addValue(NOME, xugus.getNome()));

        return jdbcTemplate.queryForObject("select * from xugus where xugus_id = :id",
                new MapSqlParameterSource("id", 4l), new XugusRowMapper());
    }
}
