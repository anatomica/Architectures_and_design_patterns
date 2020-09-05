package Homework_4.controllers;

import Homework_4.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class UserController {
    public UsersServiceImpl usersService;

    @Autowired
    public UserController(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String showUser(Model model) {
        model.addAttribute("user", usersService);
        return "profile_page";
    }

}