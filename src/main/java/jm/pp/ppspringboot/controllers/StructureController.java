package jm.pp.ppspringboot.controllers;

import jm.pp.ppspringboot.model.User;
import jm.pp.ppspringboot.service.RoleService;
import jm.pp.ppspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class StructureController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public StructureController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String userPage(Model model, Principal pr) {
        User principal = userService.getUserByEmail(pr.getName());
        model.addAttribute("principal", principal);
        return "index";
    }

    @GetMapping("/admin")
    public String info(@ModelAttribute("user") User user, ModelMap model, Principal pr) {
        model.addAttribute("principal", userService.getUserByEmail(pr.getName()));
        model.addAttribute("roles", roleService.findAll());
        return "index";
    }
}