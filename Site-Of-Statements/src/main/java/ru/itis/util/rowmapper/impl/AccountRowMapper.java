package ru.itis.util.rowmapper.impl;

import ru.itis.models.Account;
import ru.itis.models.Post;
import ru.itis.models.states.AccountRole;
import ru.itis.util.rowmapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account from(ResultSet rs, int rowNum, List<Post> posts) throws SQLException {
        return Account.builder()
                .id((UUID) rs.getObject("id"))
                .name(rs.getString("name"))
                .city(rs.getString("city"))
                .telegram(rs.getString("telegram"))
                .mail(rs.getString("mail"))
                .phoneNumber(rs.getString("phone_number"))
                .role(AccountRole.valueOf(rs.getString("role")))
                .password(rs.getString("password"))
                .build();

    }
}
