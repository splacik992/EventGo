package com.pali.eventgo.controllers.login;


import com.pali.eventgo.entity.AppUser;
import com.pali.eventgo.repository.UserRepository;
import com.pali.eventgo.services.EmailServiceImpl;
import com.pali.eventgo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
public class RegisterController {

    private Logger log = LoggerFactory.getLogger(RegisterController.class);

    private final UserService userService;
    private final EmailServiceImpl emailServiceImpl;
    private final UserRepository userRepository;
    public RegisterController(UserService userService, EmailServiceImpl emailServiceImpl, UserRepository userRepository) {
        this.userService = userService;
        this.emailServiceImpl = emailServiceImpl;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String confirmRegisterForm(@Valid @ModelAttribute("user") AppUser user, BindingResult result, Model model) {
        String confirmation = "http://localhost:9090/confirm-registration/" + user.getEmail() +
                "/" + user.getHashCodeToEnableAccount();

        if (result.hasErrors()) {
            return "register";
        }
        AppUser user1 = userService.findByUserName(user.getUsername());
        if (user1 == null) {
            userService.saveUser(user);

            try {
                emailServiceImpl.sendMail(user.getEmail(), "EventGo - Potwierdzenie rejestracji", "Aby potwierdzić rejestracje kliknij w link poniżej: \n" +
                confirmation, true);
            } catch (MessagingException e) {
                log.error(e.getMessage());
            }
        } else {
            String error = "Użytkownik już istnieje!";
            model.addAttribute("message", error);
            return "register";
        }

        return "register-confirmation";
    }

    @GetMapping("/confirm-registration/{email}/{hashCode}")
    public String confirmationRegisterForm(@PathVariable String email, @PathVariable String hashCode) {
        AppUser userByEmail = userService.findUserByEmail(email);
        if (userByEmail.getHashCodeToEnableAccount().equals(hashCode)) {
            userByEmail.setAccountEnabled(1);
            userService.updateUser(userByEmail);
        }
        return "register-confirmation";
    }
}
