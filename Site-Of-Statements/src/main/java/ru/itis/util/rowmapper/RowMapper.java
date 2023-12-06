package ru.itis.util.rowmapper;

import ru.itis.models.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface RowMapper<E> {
    E from(ResultSet rs, int rowNum, List<Post> posts) throws SQLException;
}
