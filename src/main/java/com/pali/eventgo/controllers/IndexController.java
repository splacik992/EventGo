package com.pali.eventgo.controllers;

import com.pali.eventgo.entity.Category;
import com.pali.eventgo.entity.CurrentUser;
import com.pali.eventgo.entity.Event;
import com.pali.eventgo.exceptions.ResourceAlreadyExistException;
import com.pali.eventgo.exceptions.ResourceNotExistException;
import com.pali.eventgo.repository.CategoryRepository;
import com.pali.eventgo.repository.EventRepository;
import com.pali.eventgo.repository.UserRepository;
import com.pali.eventgo.services.EventService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class
IndexController {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final EventService eventService;

    public IndexController(UserRepository userRepository, EventRepository eventRepository, CategoryRepository categoryRepository, EventService eventService) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.eventService = eventService;
    }

    @GetMapping
    public String getHomePage(Model model, Event event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        model.addAttribute("event", event);
        model.addAttribute("dateFormatter ", formatter);
        model.addAttribute("events", eventRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "home/home";
    }

    @RequestMapping(value = "event",method = RequestMethod.GET)
    public void getModelViewOfNewEvent(Model model) {
        model.addAttribute("newEvent", new Event());
    }

    @RequestMapping(value = "event",method = RequestMethod.POST)
    public String postNewEvent(@Valid Event event, BindingResult result,
                               @AuthenticationPrincipal CurrentUser currentUser) throws ResourceNotExistException, ResourceAlreadyExistException {
        if (result.hasErrors()) {
            return "/";
        }
        eventService.createNewEventByCurrentUser(event, currentUser.getUsername());
        return "redirect:/";
    }

    @ModelAttribute("categ")
    public List<Category> categories() {
        return categoryRepository.findAll();
    }
}
