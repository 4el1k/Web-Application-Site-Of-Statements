package ru.itis.services.impl;

import ru.itis.dao.repositories.AccountRepository;
import ru.itis.dto.SignUpForm;
import ru.itis.models.Account;
import ru.itis.services.SingUpService;

import java.sql.SQLException;

public class SignUpDataBaseSaveService implements SingUpService {
    private final AccountRepository accountRepository;
    public SignUpDataBaseSaveService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean singUp(SignUpForm form) {

        // ToDo RowMapper implementation
        if (accountRepository.findByMail(form.getMail(), accountRowMapper)!=null){
            return false;
        }

        // ToDo
        // replace by hash method
        String passwordHash = form.getPassword();
        Account account = Account.builder()
                .name(form.getName())
                .password(passwordHash)
                .mail(form.getMail())
                .phoneNumber(form.getPhoneNumber())
                .build();

        try {
            accountRepository.save(account);
        } catch (SQLException e) {
            throw new RuntimeException(e);
            return false;
        }

        return true;
    }
}
