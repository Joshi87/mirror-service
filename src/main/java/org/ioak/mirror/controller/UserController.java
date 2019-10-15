package org.ioak.mirror.controller;

import org.ioak.mirror.domain.AuthUser;
import org.ioak.mirror.domain.User;
import org.ioak.mirror.service.AuthService;
import org.ioak.mirror.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthService authService;


    @RequestMapping(value = "/user/test", method = RequestMethod.GET)
    public ResponseEntity<User> test (HttpServletRequest request) {
        String userId = authService.getAuthUserId();
        return ResponseEntity.ok(service.getById(authService.getAuthUserId()));
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void delete (@PathVariable("id") String id) {
        service.delete(id);
    }
}




