package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/")
    public String getAllUsers(Model model) {

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "users";
    }

    @RequestMapping(value = "/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "userInfo";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/updateInfo")
    public String updateUser(@RequestParam("userId") int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "userInfo";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}