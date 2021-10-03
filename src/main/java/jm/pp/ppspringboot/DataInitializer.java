package jm.pp.ppspringboot;

import jm.pp.ppspringboot.model.Role;
import jm.pp.ppspringboot.model.User;
import jm.pp.ppspringboot.service.RoleService;
import jm.pp.ppspringboot.service.UserService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;


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

        Role role1 = new Role();
        role1.setRole("ROLE_USER");
        Role role2 = new Role();
        role2.setRole("ROLE_ADMIN");

        User root = new User();
        root.setFirstName("FirstName");
        root.setLastName("LastName");
        root.setEmail("root");
        root.setAge(30);
        root.setPassword("1");
        root.setRole(role1);
        root.setRole(role2);

        User user = new User();
        user.setFirstName("User");
        user.setLastName("User");
        user.setEmail("user");
        user.setAge(20);
        user.setPassword("1");
        user.setRole(role1);

        roleService.save(role1);
        roleService.save(role2);
        userService.save(root);
        userService.save(user);
    }
}