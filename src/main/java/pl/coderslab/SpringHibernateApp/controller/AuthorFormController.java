package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.SpringHibernateApp.dao.AuthorDao;

@Controller
@RequestMapping("/author/form")
public class AuthorFormController {

    private final AuthorDao authorDao;

    public AuthorFormController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("authors", authorDao.findAll());
        return "/author/authorListing";
    }

}
