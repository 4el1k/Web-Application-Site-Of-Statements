package ru.itis.dao.repositories;

import ru.itis.models.Account;
import ru.itis.util.rowmapper.RowMapper;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    /**
     * @param account : account for saving in DB.
     * @return true - if account have been saved, false otherwise.
     */
    void save(Account account) throws SQLException;

    /**
     * @param id : id for deleting from DB.
     * @return : true - if account have been deleted, false otherwise.
     * */
    boolean delete(UUID id) throws SQLException;

    /**
     * @param id : account id
     *     in field posts and favoritePosts wil be fill only fields id.
     * */
    Optional<Account> findById(UUID id, RowMapper<Account> rowMapper) throws SQLException;

    /**
     * @param mail : account mail.
     * */
    Optional<Account> findByMail(String mail, RowMapper<Account> rowMapper) throws SQLException;

    /**
     * @param account : account for deleting from DB.
     * @return : true - if account have been update, false otherwise.
     * */
    boolean update(Account account) throws SQLException;
}
