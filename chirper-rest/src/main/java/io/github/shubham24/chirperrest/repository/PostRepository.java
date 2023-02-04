package io.github.shubham24.chirperrest.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import io.github.shubham24.chirperrest.model.dao.PostDAO;
import io.github.shubham24.chirperrest.model.dao.UserDAO;

@Repository
public interface PostRepository extends ListCrudRepository<PostDAO, UUID>{
    List<PostDAO> getAllPostsByUser(UserDAO user);
}
