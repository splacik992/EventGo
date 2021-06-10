package com.pali.eventgo.controllers.login;


import com.pali.eventgo.entity.AppUser;
import com.pali.eventgo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String confirmRegisterForm(@Valid @ModelAttribute("user") AppUser user, BindingResult result,
                                      final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register";
        }
        AppUser user1 = userService.findByUserName(user.getUsername());
        if (user1 == null) {
            userService.saveUser(user);

        }
        return "redirect:/";
    }
}
