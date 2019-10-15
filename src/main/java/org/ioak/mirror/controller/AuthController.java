package org.ioak.mirror.controller;

import org.ioak.mirror.config.JwtTokenUtil;
import org.ioak.mirror.controller.representation.AuthResource;
import org.ioak.mirror.domain.User;
import org.ioak.mirror.service.RandomString;
import org.ioak.mirror.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/auth/keys", method = RequestMethod.GET)
    public ResponseEntity<AuthResource.KeyResponse> getKeys () {
        AuthResource.KeyResponse saltResource = new AuthResource.KeyResponse();
        saltResource.setSalt(RandomString.nextString(20));
        saltResource.setSolution(RandomString.nextString(50));
        return ResponseEntity.ok(saltResource);
    }

    @RequestMapping(value = "/auth/signup", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(service.save(user));
    }

    @RequestMapping(value = "/auth/keys/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> getKeysByName (@PathVariable("name") String name) {
        User user = service.getByName(name);
        if (user != null) {
            return ResponseEntity.ok(user.getProblem());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

   @RequestMapping(value = "/auth/signin", method = RequestMethod.POST)
    public ResponseEntity<AuthResource.SigninResponse> signin (@Valid @RequestBody AuthResource.SigninRequest signinRequest) {
        User user = service.getByName(signinRequest.getName());
        if (user==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (user.getSolution().equals(signinRequest.getSolution())) {

            final String[] tokenArray = jwtTokenUtil.generateToken(user);
            AuthResource.SigninResponse signinResponse = new AuthResource.SigninResponse();
            signinResponse.setToken(tokenArray[0]);
            signinResponse.setSecret(tokenArray[1]);
            signinResponse.setFirstname(user.getFirstName());
            signinResponse.setLastname(user.getLastName());
            return ResponseEntity.ok(signinResponse);
        } else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
