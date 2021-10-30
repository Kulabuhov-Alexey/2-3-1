package web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/")
    public String getAllUsers(Model model) {

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "users";
    }

    @RequestMapping(value = "/addNewUser")
    public ModelAndView addNewUser() {
        ModelAndView modelAndView = new ModelAndView("userInfo");
        User user = new User();
        modelAndView.addObject("user", user);
        Set<Role> roleSet = roleService.getAllRoles();
        modelAndView.addObject("allRoles", roleSet);
        return modelAndView;
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam Map<String, String> form) {
        user.setRoles(roleService.getRolesFromForm(roleService.getAllRoles(), form));
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/updateInfo")
//    @PreAuthorize("#userId == authentication.principal.userId or hasAuthority('ADMIN')")
    @PreAuthorize("#id == authentication.principal.id or hasAuthority('ROLE_ADMIN')")
    public ModelAndView updateUser(@RequestParam("userId") Long id, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("userInfo");
        User user = userService.getUser(id);
        modelAndView.addObject("user", user);
        Set<Role> roleSet = roleService.getAllRoles();
        modelAndView.addObject("allRoles", roleSet);
        return modelAndView;
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}