package jm.pp.ppspringboot.service;

import jm.pp.ppspringboot.model.Role;
import java.util.List;

public interface RoleService {

    void save(Role role);
    List<Role> findAll();
    Role getRoleById(Long id);
    Role getRoleByName(String role);
}
