package org.ioak.mirror.repository;

import org.ioak.mirror.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByName(String name);
}
