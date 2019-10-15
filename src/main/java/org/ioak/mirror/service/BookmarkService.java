package org.ioak.mirror.service;

import org.ioak.mirror.domain.Bookmark;
import org.ioak.mirror.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository repository;

    public List<Bookmark> getAllPosts() {
        return repository.findAll();
    }

    public List<Bookmark> getBookmarkByUser(String userId) {
        return repository.findByUserId(userId);
    }

    public Bookmark createOrUpdate(Bookmark bookmark) {
        return repository.save(bookmark);
    }

    public void delete(Bookmark bookmark) {
        repository.delete(bookmark);
    }

    public void deleteByUserId(String userId) {
        repository.deleteByUserId(userId);
    }
}
