package jm.pp.ppspringboot.controllers;

import jm.pp.ppspringboot.model.User;
import jm.pp.ppspringboot.service.RoleService;
import jm.pp.ppspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String userPage(Model model, Principal pr) {
        User principal = userService.getUserByUsername(pr.getName());
        model.addAttribute("principal", principal);
        return "user";
    }

    @GetMapping("/admin")
    public String index(@ModelAttribute("user") User user, Model model, Principal pr) {
        model.addAttribute("principal", userService.getUserByUsername(pr.getName()));
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("allRoles", roleService.findAll());
        return "admin";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model, Principal pr) {
        User principal = userService.getUserByUsername(pr.getName());
        model.addAttribute("principal", principal);
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "new";
    }

    @PostMapping("/admin/new")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam("rolesSelected") Long[] rolesId) {
        for(Long roleId: rolesId) {
            user.setRole(roleService.getRoleById(roleId));
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/admin/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") User user,
                         @RequestParam("rolesSelected") Long[] rolesId) {
        for(Long roleId: rolesId) {
            user.setRole(roleService.getRoleById(roleId));
        }
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/admin";
    }
}