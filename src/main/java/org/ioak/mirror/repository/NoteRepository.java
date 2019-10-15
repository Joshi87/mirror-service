package org.ioak.mirror.repository;

import org.ioak.mirror.domain.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NoteRepository extends MongoRepository<Note, String> {
        Optional<Note> findById(String id);
        void deleteById(String id);
}
