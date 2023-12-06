package ru.itis.dto;

import lombok.Builder;
import lombok.Getter;
import ru.itis.models.states.PostStatus;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class SavePostForm {
    private UUID accountId;
    private String title;
    private String description;
    private List<String> pathsOfPhotos;
    private int price;
}
