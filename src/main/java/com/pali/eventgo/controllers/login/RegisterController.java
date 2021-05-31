package com.pali.eventgo.controllers.login;


import com.pali.eventgo.entity.AppUser;
import com.pali.eventgo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String confirmRegisterForm(@Valid AppUser user, BindingResult result, Model model){

        if(result.hasErrors()){
            return "register";
        }
        AppUser user1 = userService.findByUserName(user.getUsername());
        if(user1==null){
            userService.saveUser(user);
        }else {
            String error = "Użytkownik już istnieje!";
            model.addAttribute("message",error);
            return "register";
        }

        return "redirect:/api/v1/home";
    }
}
