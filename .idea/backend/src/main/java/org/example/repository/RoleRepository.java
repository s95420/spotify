package com.bialy.spotifydatarestapi.repository;

import com.bialy.spotifydatarestapi.model.ERole;
import com.bialy.spotifydatarestapi.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
