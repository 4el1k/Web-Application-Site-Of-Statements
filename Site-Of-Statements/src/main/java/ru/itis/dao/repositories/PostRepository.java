package ru.itis.dao.repositories;

import ru.itis.models.Post;
import ru.itis.util.rowmapper.RowMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository {
    void save(Post post) throws SQLException;
    // in instance of Post account will be only with id field.
    Optional<Post> findById(UUID id, RowMapper<Post> rowMapper) throws SQLException;
    List<Post> getSome(int amount, RowMapper<Post> rowMapper) throws SQLException;
    boolean delete(UUID id) throws SQLException;
    boolean update(UUID id, Post post) throws SQLException;
}
