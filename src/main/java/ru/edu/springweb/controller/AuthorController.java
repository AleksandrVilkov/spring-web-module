package ru.edu.springweb.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private static final String HOBBY_PAGE = "author";

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getAuthor(Model model) {
        model.addAttribute("author", "Александр Вилков");
        return new ModelAndView(HOBBY_PAGE);
    }
}
