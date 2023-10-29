package ru.itis.services.impl;

import ru.itis.dao.repositories.AccountRepository;
import ru.itis.models.Account;
import ru.itis.services.FindAccountService;
import ru.itis.util.rowmapper.impl.AccountRowMapper;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class FindAccountInDataBaseService implements FindAccountService {
    private final AccountRepository accountRepository;
    public FindAccountInDataBaseService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> find(UUID id) {
        try {
            return accountRepository.findById(id, new AccountRowMapper());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Account> find(String mail) {
        try {
            return accountRepository.findByMail(mail, new AccountRowMapper());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
