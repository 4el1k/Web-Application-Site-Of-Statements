package ru.itis.models.util.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<E> {
    E from(ResultSet rs, int rowNum) throws SQLException;
}
