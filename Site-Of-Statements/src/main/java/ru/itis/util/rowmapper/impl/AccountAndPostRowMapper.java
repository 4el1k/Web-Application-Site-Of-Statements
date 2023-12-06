package ru.itis.util.rowmapper.impl;

import ru.itis.models.Account;
import ru.itis.models.Post;
import ru.itis.models.states.AccountRole;
import ru.itis.models.states.PostStatus;
import ru.itis.util.rowmapper.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AccountAndPostRowMapper implements RowMapper<Account> {

    @Override
    public Account from(ResultSet rs, int rowNum, List<Post> posts) throws SQLException {
        Account account = Account.builder()
                .id((UUID) rs.getObject("id"))
                .name(rs.getString("name"))
                .city(rs.getString("city"))
                .telegram(rs.getString("telegram"))
                .mail(rs.getString("mail"))
                .phoneNumber(rs.getString("phone_number"))
                .role(AccountRole.valueOf(rs.getString("role")))
                .password(rs.getString("password"))
                .build();
        Array s = rs.getArray("paths_to_photos");
        Post post = Post.builder()
                .price(rs.getInt("price"))
                .title(rs.getString("title"))
                .status(PostStatus.valueOf(rs.getString("status")))
                .description(rs.getString("description"))
                .id((UUID) rs.getObject("id"))
                .publishingTime(rs.getDate("publishing_time"))
                .pathsOfPhotos(Arrays.asList((String[]) s.getArray()))
                .account(account)
                .build();
        posts.add(post);
        account.setPosts(posts);
        return account;
    }
}
