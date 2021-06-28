package com.pali.eventgo.controllers.login;


import com.pali.eventgo.entity.AppUser;
import com.pali.eventgo.services.EmailService;
import com.pali.eventgo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserService userService;
    private final EmailService emailService;
    public RegisterController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String confirmRegisterForm(@Valid @ModelAttribute("user") AppUser user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        AppUser user1 = userService.findByUserName(user.getUsername());
        if (user1 == null) {
            userService.saveUser(user);
            String confirmation = "http://localhost:9090/register/confirm-registration/" + user.getEmail() +
                    "/" + user.getHashCodeToEnableAccount();
            emailService.prepareAndSend(user.getEmail(), "Aby potwierdzić rejestracje kliknij w link poniżej: \n" +
                    confirmation, "EventGo - Potwierdzenie rejestracji");
        } else {
            String error = "Użytkownik już istnieje!";
            model.addAttribute("message", error);
            return "register";
        }

        return "redirect:/";
    }

    @GetMapping("confirm-registration/{email}/{hashCode}")
    public String confirmationRegisterForm(@PathVariable String email, @PathVariable String hashCode) {
        AppUser userByEmail = userService.findUserByEmail(email);
        if (userByEmail.getHashCodeToEnableAccount().equals(hashCode)) {
            userByEmail.setAccountEnabled(1);
            userService.updateUser(userByEmail);
        }
        return "register-confirmation";
    }
}
