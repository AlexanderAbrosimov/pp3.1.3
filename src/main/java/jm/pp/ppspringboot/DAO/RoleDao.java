package jm.pp.ppspringboot.DAO;

import jm.pp.ppspringboot.model.Role;

import java.util.List;

public interface RoleDao {
    void save(Role role);
    List<Role> findAll();
    Role getRoleById(Long id);
    Role getRoleByName(String role);
}
