package ru.itis.services;

import ru.itis.models.Account;

import java.util.Optional;
import java.util.UUID;

public interface FindAccountService {
    Optional<Account> find(UUID id);
    Optional<Account> find(String mail);
}
