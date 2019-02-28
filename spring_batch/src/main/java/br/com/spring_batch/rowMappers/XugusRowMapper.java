package br.com.spring_batch.rowMappers;

import br.com.spring_batch.entities.Xugus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class XugusRowMapper implements RowMapper<Xugus> {
    @Override
    public Xugus mapRow(ResultSet rs, int rowNum) throws SQLException {
        Xugus xugus = new Xugus();
        xugus.setPerson_id(rs.getLong("person_id"));
        xugus.setNome(rs.getString("nome"));
        xugus.setXugus_id(rs.getLong("xugus_id"));

        return xugus;
    }
}
