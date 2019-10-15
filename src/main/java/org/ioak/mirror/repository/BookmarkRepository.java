package org.ioak.mirror.repository;

import org.ioak.mirror.domain.Bookmark;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends MongoRepository<Bookmark, String> {
    List<Bookmark> findAll();
    List<Bookmark> findByUserId(String userId);
    void deleteByUserId(String userId);
}
