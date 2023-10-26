package ru.itis.dao.repositories.impl;

import ru.itis.dao.repositories.AccountRepository;
import ru.itis.models.Account;
import ru.itis.util.rowmapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class AccountRepositoryJDBC implements AccountRepository {
    private final Connection connection;
    private final String SAVE_SQL;
    private final String FIND_BY_ID_SQL;
    private final String FIND_BY_MAIL_SQL;
    private final String DELETE_SQL;
    private final String UPDATE_SQL;
    private PreparedStatement savePS;
    private PreparedStatement deletePS;
    private PreparedStatement findByIdPS;
    private PreparedStatement findByMailPS;
    private PreparedStatement updatePS;

    public AccountRepositoryJDBC(Connection connection) {
        this.connection = connection;
        // language=sql
        SAVE_SQL = "INSERT INTO accounts (name, city, telegram, mail, phone_number, role, password)"
                + "VALUES (?, ?, ?, ?, ?, ?,? )";
        // language=sql
        FIND_BY_ID_SQL = "SELECT * FROM accounts WHERE id = ?";
        // language=sql
        DELETE_SQL = null;
        // language=sql
        UPDATE_SQL = "UPDATE accounts SET name = ?, city = ?, telegram = ?, mail = ?, phone_number = ?, role = ?" +
                "WHERE id = ?";
        // language = sql
        FIND_BY_MAIL_SQL = null;
    }

    @Override
    public void save(Account account) throws SQLException {
        checkPS(savePS, SAVE_SQL);
        try {
           savePS.setString(1, account.getName());
           savePS.setString(2, account.getCity());
           savePS.setString(3, account.getTelegram());
           savePS.setString(4, account.getMail());
           savePS.setString(5, account.getPhoneNumber());
           savePS.setString(6, account.getRole().name());
           savePS.setString(7, account.getPassword());

           savePS.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // TODO 
    @Override
    public boolean delete(UUID id) throws SQLException {
        return false;
    }

    @Override
    public Optional<Account> findById(UUID id, RowMapper<Account> rowMapper) throws SQLException {
        checkPS(findByIdPS, FIND_BY_ID_SQL);
        Account resultAccount = null;
        try {
            findByIdPS.setObject(1, id);
            ResultSet resultSet = findByIdPS.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                resultAccount = rowMapper.from(resultSet, i);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (resultAccount==null){
            return Optional.empty();
        }
        return Optional.of(resultAccount);
    }

    @Override
    public Optional<Account> findByMail(String mail, RowMapper<Account> rowMapper) throws SQLException {
        checkPS(findByMailPS, FIND_BY_MAIL_SQL);
        Account resultAccount = null;
        try {
            findByMailPS.setObject(1, mail);
            ResultSet resultSet = findByMailPS.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                resultAccount = rowMapper.from(resultSet, i);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (resultAccount==null){
            return Optional.empty();
        }
        return Optional.of(resultAccount);
    }

    @Override
    public boolean update(Account account) throws SQLException {
        checkPS(updatePS, UPDATE_SQL);
        try{
            updatePS.setString(1, account.getName());
            updatePS.setString(2, account.getCity());
            updatePS.setString(3, account.getTelegram());
            updatePS.setString(4, account.getMail());
            updatePS.setString(5, account.getPhoneNumber());
            updatePS.setString(6, account.getRole().name());

            updatePS.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
    
    private void checkPS(PreparedStatement ps, String sql){
        if (ps==null){
            try {
                ps = connection.prepareStatement(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
