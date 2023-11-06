package ru.itis.util.rowmapper.impl;

import ru.itis.models.Account;
import ru.itis.models.Post;
import ru.itis.models.states.PostStatus;
import ru.itis.util.rowmapper.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post from(ResultSet rs, int rowNum) throws SQLException {
        Array s = rs.getArray("paths_to_photos");
        Account account = Account.builder()
                .id((UUID) rs.getObject("account_id"))
                .build();
        return Post.builder()
                .price(rs.getInt("price"))
                .title(rs.getString("title"))
                .status(PostStatus.valueOf(rs.getString("status")))
                .description(rs.getString("description"))
                .id((UUID) rs.getObject("id"))
                .publishingTime(rs.getDate("publishing_time"))
                .pathsOfPhotos(Arrays.asList((String[]) s.getArray()))
                .account(account)
                .build();
    }
}








