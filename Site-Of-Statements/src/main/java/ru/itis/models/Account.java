package ru.itis.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.itis.models.states.AccountRole;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class Account {
    private UUID id;
    private String name;
    private String city;
    private String telegram;
    private String mail;
    private String phoneNumber;
    private AccountRole role;
    private List<Post> posts;
    private List<Post> favoritePosts;
}
