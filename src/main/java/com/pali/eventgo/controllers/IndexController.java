package com.pali.eventgo.controllers;

import com.pali.eventgo.entity.Event;
import com.pali.eventgo.repository.CategoryRepository;
import com.pali.eventgo.repository.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(value = "/api/v1")
public class IndexController {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;

    public IndexController(EventRepository eventRepository, CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
    }
    @GetMapping(value = "/home")
    public String getHomePage(Model model, Event event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        model.addAttribute("event", event);
        model.addAttribute("dateFormatter ", formatter);
        model.addAttribute("events", eventRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "home/home";
    }
}
