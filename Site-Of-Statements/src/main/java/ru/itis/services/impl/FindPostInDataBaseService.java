package ru.itis.services.impl;

import ru.itis.dao.repositories.AccountRepository;
import ru.itis.dao.repositories.PostRepository;
import ru.itis.models.Post;
import ru.itis.services.FindPostService;
import ru.itis.util.rowmapper.impl.PostRowMapper;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class FindPostInDataBaseService implements FindPostService {
    PostRepository postRepository;
    public FindPostInDataBaseService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public Optional<Post> findPost(UUID id) {
        Optional<Post> postOptional;
        try {
            postOptional = postRepository.findById(id, new PostRowMapper());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return postOptional;
    }
}
