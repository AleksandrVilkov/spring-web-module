package ru.edu.springweb.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hobby")
public class HobbyController {

    private static final String HOBBY_PAGE = "hobby";

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getHobby(Model model) {
        model.addAttribute("hobby", "Автомобили");
        return new ModelAndView(HOBBY_PAGE);
    }
}
