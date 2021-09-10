package jm.pp.ppspringboot.service;

import jm.pp.ppspringboot.model.User;

import java.util.List;


public interface UserService {

    List<User> findAllUsers();
    void save(User user);
    void update(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    void delete(Long id);

}
