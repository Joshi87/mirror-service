package org.ioak.mirror.controller;

import org.ioak.mirror.domain.Note;
import org.ioak.mirror.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Note> getAllNotes () {
        return service.getAllNotes();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Note saveNote (@Valid @RequestBody Note note) {
        return service.createOrUpdate(note);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Note getNote (@PathVariable("id") String noteid) {
        return service.getNote(noteid);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Note put (@Valid @RequestBody Note note) {
        return service.createOrUpdate(note);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable("id") String id) {
        service.deleteById(id);
    }

}


