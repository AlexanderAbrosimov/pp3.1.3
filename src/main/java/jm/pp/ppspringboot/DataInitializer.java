package jm.pp.ppspringboot;

import jm.pp.ppspringboot.model.Role;
import jm.pp.ppspringboot.model.User;
import jm.pp.ppspringboot.service.RoleService;
import jm.pp.ppspringboot.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer {
    private final UserService userService;
    private final RoleService roleService;

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void init() {

        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        Set<Role> allRoles = new HashSet<>();
        Set<Role> userRole = new HashSet<>();
        allRoles.add(role1);
        allRoles.add(role2);
        userRole.add(role2);
        User root = new User("FirstName", "LastName", "root", 30, "1", allRoles);
        User user = new User("user", "user", "user@mail.ru", 20, "1", userRole);

        roleService.save(role1);
        roleService.save(role2);
        userService.save(root);
        userService.save(user);

    }
}