package com.pali.eventgo.controllers;

import com.pali.eventgo.entity.Category;
import com.pali.eventgo.entity.CurrentUser;
import com.pali.eventgo.entity.Event;
import com.pali.eventgo.exceptions.ResourceAlreadyExistException;
import com.pali.eventgo.exceptions.ResourceNotExistException;
import com.pali.eventgo.repository.CategoryRepository;
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

    private final static String NAME_TAKEN_MESSAGE = "Nazwa jest już zajęta";

    private final CategoryRepository categoryRepository;
    private final EventService eventService;

    public IndexController( CategoryRepository categoryRepository, EventService eventService) {
        this.categoryRepository = categoryRepository;
        this.eventService = eventService;
    }

    @GetMapping
    public String getHomePage(Model model, Event event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        model.addAttribute("event", event);
        model.addAttribute("dateFormatter ", formatter);
        model.addAttribute("events", eventService.findAllEventsByIdDesc());
        model.addAttribute("categories", categoryRepository.findAll());
        return "home/home";
    }

    @PostMapping(value = "event/place")
    public String searchEventByName(Model model,@RequestParam String eventSearchByPlace) throws ResourceNotExistException {
        List<Event> eventsByPlace = eventService.findEventsByPlace2(eventSearchByPlace);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("events",eventsByPlace);
        return "home/home";
    }

    @PostMapping(value = "event/name")
    public String searchEventByPlace(Model model,@RequestParam String eventSearchByName) throws ResourceNotExistException {
        List<Event> eventsByName = eventService.findEventsByName(eventSearchByName);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("events",eventsByName);
        return "home/home";
    }



    @RequestMapping(value = "event",method = RequestMethod.GET)
    public void getModelViewOfNewEvent(Model model) {
        model.addAttribute("event", new Event());
    }

    @RequestMapping(value = "event",method = RequestMethod.POST)
    public String postNewEvent(@Valid @ModelAttribute("event") Event event, BindingResult result,
                               @AuthenticationPrincipal CurrentUser currentUser
                              ) throws ResourceNotExistException, ResourceAlreadyExistException {

        if (result.hasErrors()) {
            return "/home/home";
        }

        eventService.createNewEventByCurrentUser(event, currentUser.getUsername());
        return "redirect:/";
    }

    @ModelAttribute("categ")
    public List<Category> categories() {
        return categoryRepository.findAll();
    }
}
