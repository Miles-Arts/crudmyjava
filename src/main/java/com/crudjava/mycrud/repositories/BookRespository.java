package com.crudjava.mycrud.repositories;

import com.crudjava.mycrud.model.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRespository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert insert;

    private final BookMapper mapper = new BookMapper();

    private final String table = "books";

    public BookRespository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataSource dataSource){
        this.jdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("id");
    }

    public List<Book> getAllBooks() {
        String sql = "select * from " + table;
        return jdbcTemplate.query(sql, mapper);
    }

    public long createBook(Book newBook) {
        return insert.executeAndReturnKey(
                new MapSqlParameterSource("name", newBook.name)
        ).longValue();
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
