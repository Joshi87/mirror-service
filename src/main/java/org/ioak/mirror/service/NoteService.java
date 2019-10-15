package org.ioak.mirror.service;

import org.ioak.mirror.domain.Note;
import org.ioak.mirror.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository repository;

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    public Note getNote(String id) {
        return repository.findById(id).get();
    }

    public Note createOrUpdate(Note note) {
        return repository.save(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public void deleteById(String noteid) {
        repository.deleteById(noteid);
    }

}
