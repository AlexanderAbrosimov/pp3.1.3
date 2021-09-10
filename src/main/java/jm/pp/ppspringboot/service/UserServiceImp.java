package jm.pp.ppspringboot.service;

import jm.pp.ppspringboot.DAO.UserDao;
import jm.pp.ppspringboot.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

   private final UserDao userDao;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;

   public UserServiceImp(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
      this.userDao = userDao;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   }

   @Override
   public List<User> findAllUsers() {
      return userDao.findAll();
   }

   @Override
   public void save(User user) {
      user.setPassword(new BCryptPasswordEncoder(10).encode(user.getPassword()));
      userDao.save(user);
   }

   @Override
   public User getUserById(Long id) {
      return userDao.getUserById(id);
   }

   @Override
   public User getUserByUsername(String username) {
      return userDao.getUserByUsername(username);
   }


   @Override
   public void update(User user) {
      userDao.update(user);
   }


   @Override
   public void delete(Long id) {
      userDao.delete(id);
   }

}
