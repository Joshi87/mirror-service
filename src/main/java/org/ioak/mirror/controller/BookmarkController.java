package org.ioak.mirror.controller;

import org.ioak.mirror.domain.Bookmark;
import org.ioak.mirror.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

    @Autowired
    private BookmarkService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Bookmark> getAllBookmarks () {
        return service.getAllPosts();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Bookmark saveBookmark (@Valid @RequestBody Bookmark bookmark) {
        return service.createOrUpdate(bookmark);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public List<Bookmark> getBookmarkByUser (@PathVariable("userId") String userId) {
        return service.getBookmarkByUser(userId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Bookmark put (@Valid @RequestBody Bookmark bookmark) {
        return service.createOrUpdate(bookmark);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public void delete (@PathVariable("userId") String userId) {
        service.deleteByUserId(userId);
    }

}


