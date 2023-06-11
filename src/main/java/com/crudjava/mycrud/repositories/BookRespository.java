package com.crudjava.mycrud.repositories;

import com.crudjava.mycrud.model.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRespository {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    private final BookMapper mapper = new BookMapper();

    public BookRespository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Book> getAllBooks() {
        String sql = "select * from books";
        return jdbcTemplate.query(sql, mapper);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");

            return new Book(id, name);
        }
    }
}
