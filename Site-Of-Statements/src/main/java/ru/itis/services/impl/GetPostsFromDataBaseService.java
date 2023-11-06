package ru.itis.services.impl;

import ru.itis.dao.repositories.PostRepository;
import ru.itis.models.Post;
import ru.itis.services.GetPostsService;
import ru.itis.util.rowmapper.impl.PostRowMapper;

import java.sql.SQLException;
import java.util.List;

public class GetPostsFromDataBaseService implements GetPostsService {
    private PostRepository postRepository;
    public GetPostsFromDataBaseService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getPosts(int amount) {
        try {
            return postRepository.getSome(amount, new PostRowMapper());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
