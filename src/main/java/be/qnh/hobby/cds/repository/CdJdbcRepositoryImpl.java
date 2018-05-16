package be.qnh.hobby.cds.repository;

import be.qnh.hobby.cds.domain.Artist;
import be.qnh.hobby.cds.domain.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CdJdbcRepositoryImpl implements CdJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CdJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CompactDisc> findAll() {
        return jdbcTemplate.query("SELECT * FROM compact_disc",
                (resultSet, i) -> makeCD(resultSet));
    }

    private static CompactDisc makeCD(ResultSet resultSet) {
        CompactDisc cd = new CompactDisc();
        try {
            cd.setArtist((Artist) resultSet.getObject("artist_id"));
            cd.setDuration(resultSet.getString("duration"));
            cd.setYear(resultSet.getString("year"));
            cd.setAlbum(resultSet.getString("album"));
            cd.setId(resultSet.getLong("id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cd;
    }
}
