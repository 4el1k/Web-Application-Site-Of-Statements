package ru.itis.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.itis.models.states.PostStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class Post {
    private UUID id;
    private Account account;
    private String title;
    private String description;
    private PostStatus status;
    private Date publishingTime;
    private List<String> pathsOfPhotos;
    private int price;
}
