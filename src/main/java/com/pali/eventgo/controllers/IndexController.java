package com.pali.eventgo.controllers;

import com.pali.eventgo.entity.Category;
import com.pali.eventgo.entity.CurrentUser;
import com.pali.eventgo.entity.Event;
import com.pali.eventgo.exceptions.ResourceAlreadyExistException;
import com.pali.eventgo.exceptions.ResourceNotExistException;
import com.pali.eventgo.repository.CategoryRepository;
import com.pali.eventgo.services.EventService;
import com.pali.eventgo.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(value = "/")
@Validated
public class IndexController {

    private final static String NAME_TAKEN_MESSAGE = "Nazwa jest już zajęta";

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);
    private final CategoryRepository categoryRepository;
    private final EventService eventService;
    private final UserServiceImpl userService;

    public IndexController(CategoryRepository categoryRepository, EventService eventService, UserServiceImpl userService) {
        this.categoryRepository = categoryRepository;
        this.eventService = eventService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHomePage(Model model, Event event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        model.addAttribute("event", new Event());
        model.addAttribute("dateFormatter ", formatter);
        model.addAttribute("events", eventService.findAllEventsByIdDesc());
        model.addAttribute("categories", categoryRepository.findAll());
        return "home/home";
    }

    @RequestMapping(value = "event/name", method = RequestMethod.GET)
    public String searchEventsByPlace(Model model, @RequestParam String eventSearchByName) throws ResourceNotExistException {
        List<Event> eventsByName = eventService.findEventsByName(eventSearchByName);
        model.addAttribute("event", new Event());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("events", eventsByName);
        return "home/home";
    }

    @RequestMapping(value = "event/category/{eventSearchByCategory}", method = RequestMethod.GET)
    public String sortEventsByCategory(Model model, @PathVariable String eventSearchByCategory) throws ResourceNotExistException {
        List<Event> eventsByCategory = eventService.findEventsByCategory(eventSearchByCategory);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("eventsByCategory", eventsByCategory);
        model.addAttribute("event", new Event());
        return "home/home";
    }


    @RequestMapping(value = "event", method = RequestMethod.GET)
    public void getModelViewOfNewEvent(Model model) {
        model.addAttribute("event", new Event());
    }


    @RequestMapping(value = "event", method = RequestMethod.POST)
    public String postNewEvent(@Valid @ModelAttribute("event") Event event, BindingResult result,
                               @AuthenticationPrincipal CurrentUser currentUser
    ) throws ResourceNotExistException, ResourceAlreadyExistException {

        if (result.hasErrors()) {
            return "/home/home";
        }
        event.setUser(userService.findByUserName(currentUser.getAppUser().getUsername()));
        eventService.createNewEventByCurrentUser(event, currentUser.getUsername());
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotExistException.class)
    public ModelAndView handleNotFound(Exception exception) {
        logger.error("Handling not found exception");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

    @ModelAttribute("categ")
    public List<Category> categories() {
        return categoryRepository.findAll();
    }
}
