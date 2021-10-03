package jm.pp.ppspringboot.DAO;

import jm.pp.ppspringboot.model.User;

import java.util.List;

public interface UserDao {
   List<User> findAllUsers();
   User getUserByEmail(String Email);
   void update(User user);
   void save(User user);
   User getUserById(Long id);
   void delete(Long id);
}
