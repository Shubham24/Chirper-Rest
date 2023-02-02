package io.github.shubham24.chirperrest.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import io.github.shubham24.chirperrest.model.dao.UserDAO;

@Repository
public interface UserRepository extends ListCrudRepository<UserDAO, UUID>{

    Optional<UserDAO> findByUsername(String username);
    
}
