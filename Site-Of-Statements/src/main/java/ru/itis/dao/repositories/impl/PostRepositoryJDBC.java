package ru.itis.dao.repositories.impl;

import ru.itis.dao.repositories.PostRepository;
import ru.itis.models.Post;
import ru.itis.util.rowmapper.RowMapper;

import java.sql.*;
import java.util.*;

public class PostRepositoryJDBC implements PostRepository {
    private final Connection connection;
    private final String SAVE_SQL;
    private final String FIND_BY_ID_SQL;
    private final String GET_SOME_SQL;
    private final String DELETE_SQL;
    private final String UPDATE_SQL;
    private PreparedStatement savePS;
    private PreparedStatement findByIdPS;
    private PreparedStatement getSomePS;
    private PreparedStatement deletePS;
    private PreparedStatement updatePS;

    public PostRepositoryJDBC(Connection connection) {
        this.connection = connection;
        // language=sql
        SAVE_SQL = "INSERT INTO posts (account_id, title, description, status, price, paths_to_photos)"
                + "VALUES (?,?,?,?,?,?)";
        // language=sql
        FIND_BY_ID_SQL = "SELECT * FROM posts WHERE id = ?";
        //language=sql
        GET_SOME_SQL = "SELECT * FROM posts ORDER BY publishing_time DESC LIMIT 10";
        // language=sql
        DELETE_SQL = null;
        // language=sql
        UPDATE_SQL = "UPDATE posts SET title = ?, description = ?, status = ?, price = ?, paths_to_photos = ?";
    }

    @Override
    public void save(Post post) throws SQLException{
        if (savePS == null) {
            try {
                savePS = connection.prepareStatement(SAVE_SQL);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            savePS.setObject(1, post.getAccount().getId());
            savePS.setString(2, post.getTitle());
            savePS.setString(3, post.getDescription());
            savePS.setString(4, post.getStatus().name());
            savePS.setInt(5, post.getPrice());
            savePS.setArray(6, connection.createArrayOf("text",
                    post.getPathsOfPhotos().toArray(new String[0])));

            savePS.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // in instance of Post account will be only with id field.
    @Override
    public Optional<Post> findById(UUID id, RowMapper<Post> postRowMapper) throws SQLException{
        if (findByIdPS == null) {
            try {
                findByIdPS = connection.prepareStatement(FIND_BY_ID_SQL);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        Post resultPost = null;
        try {
            findByIdPS.setObject(1, id);
            ResultSet resultSet = findByIdPS.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                resultPost = postRowMapper.from(resultSet, i);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (resultPost==null){
            return Optional.empty();
        }
        return Optional.of(resultPost);
    }

    @Override
    public List<Post> getSome(int amount, RowMapper<Post> rowMapper) throws SQLException{
        if (getSomePS==null){
            try {
                getSomePS = connection.prepareStatement(GET_SOME_SQL);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        List<Post> result = new ArrayList<>();
        try {
            //getSomePS.setInt(1, amount);
            ResultSet resultSet = getSomePS.executeQuery();
            int i = 0;
            while (resultSet.next()){
                result.add(rowMapper.from(resultSet, i));
                i++;
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO
    @Override
    public boolean delete(UUID id) throws SQLException {
        return false;
    }

    // TODO
    @Override
    public boolean update(UUID id, Post post) throws SQLException {
        return false;
    }
}
