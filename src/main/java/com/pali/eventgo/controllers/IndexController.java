package com.pali.eventgo.controllers;

import com.pali.eventgo.entity.Category;
import com.pali.eventgo.entity.CurrentUser;
import com.pali.eventgo.entity.Event;
import com.pali.eventgo.exceptions.ResourceAlreadyExistException;
import com.pali.eventgo.exceptions.ResourceNotExistException;
import com.pali.eventgo.repository.CategoryRepository;
import com.pali.eventgo.services.EventService;
import com.pali.eventgo.utils.ImageUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    private final static String NAME_TAKEN_MESSAGE = "Nazwa jest już zajęta";

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);
    private final ImageUploader imageUploader;
    private final CategoryRepository categoryRepository;
    private final EventService eventService;

    public IndexController(ImageUploader imageUploader, CategoryRepository categoryRepository, EventService eventService) {
        this.imageUploader = imageUploader;
        this.categoryRepository = categoryRepository;
        this.eventService = eventService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHomePage(Model model, Event event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        model.addAttribute("event", event);
        model.addAttribute("dateFormatter ", formatter);
        model.addAttribute("events", eventService.findAllEventsByIdDesc());
        model.addAttribute("categories", categoryRepository.findAll());
        return "home/home";
    }
    @RequestMapping(value = "event/place",method = RequestMethod.POST)
    public String searchEventByName(Model model,@RequestParam String eventSearchByPlace) throws ResourceNotExistException {
        List<Event> eventsByPlace = eventService.findEventsByPlace2(eventSearchByPlace);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("events",eventsByPlace);
        return "home/home";
    }
    @RequestMapping(value = "event/name",method = RequestMethod.POST)
    public String searchEventsByPlace(Model model, @RequestParam String eventSearchByName) throws ResourceNotExistException {
        List<Event> eventsByName = eventService.findEventsByName(eventSearchByName);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("events",eventsByName);
        return "home/home";
    }

    @RequestMapping(value = "event/category/{eventSearchByCategory}",method = RequestMethod.GET)
    public String sortEventsByCategory(Model model,@PathVariable String eventSearchByCategory) throws ResourceNotExistException {
        List<Event> eventsByName = eventService.findEventsByCategory(eventSearchByCategory);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("eventsByCategory",eventsByName);
        return "home/home";
    }


    @RequestMapping(value = "event",method = RequestMethod.GET)
    public void getModelViewOfNewEvent(Model model) {
        model.addAttribute("event", new Event());
    }


    @RequestMapping(value = "event",method = RequestMethod.POST)
    public String postNewEvent(@Valid @ModelAttribute("event") Event event, BindingResult result,
                               @AuthenticationPrincipal CurrentUser currentUser
                               ,@RequestParam (value = "imagePath",required = false) String imagePath
                              ) throws ResourceNotExistException, ResourceAlreadyExistException {

        if (result.hasErrors()) {
            return "/home/home";
        }

         eventService.createNewEventByCurrentUser(event, currentUser.getUsername(),imagePath);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotExistException.class)
    public ModelAndView handleNotFound(Exception exception){
        logger.error("Handling not found exception");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleAlreadyExist(Exception exception){
        logger.error("Handling Number Format Exception");

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
