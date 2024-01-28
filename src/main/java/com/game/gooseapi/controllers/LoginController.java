package com.game.gooseapi.controllers;

import com.game.gooseapi.models.Sessions;
import com.game.gooseapi.models.User;
import com.game.gooseapi.repositories.SessionRepository;
import com.game.gooseapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }


    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute User user, Model model) {
        Sessions session = new Sessions(user.getUsername() + "Session");
        session.setUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        sessionRepository.save(session);
        return "redirect:/pageForUsers";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }


    @GetMapping("/pageForUsers")
    public String getPageForUsers() {
        return "pageForUsers";
    }
}
