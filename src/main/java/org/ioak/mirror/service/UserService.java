package org.ioak.mirror.service;

import org.ioak.mirror.domain.User;
import org.ioak.mirror.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User getByName(String name) {
        return repository.findByName(name);
    }

    public User getById(String userId) {
        return repository.findById(userId).orElse(null);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

}
