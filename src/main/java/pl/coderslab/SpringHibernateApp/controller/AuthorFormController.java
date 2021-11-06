package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateApp.dao.AuthorDao;
import pl.coderslab.SpringHibernateApp.entity.Author;

import javax.validation.Valid;

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

    @GetMapping("/add")
    public String prepareAdd(Model model) {
        model.addAttribute("author", new Author());
        return "/author/authorForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("author") @Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "/author/authorForm";
        }
        authorDao.persist(author);
        return "redirect:/author/form/all";
    }

    @GetMapping("/edit")
    public String prepareEdit(@RequestParam long id, Model model) {
        model.addAttribute("author", authorDao.findById(id));
        return "/author/authorForm";
    }

    @PostMapping("/edit")
    public String merge(@ModelAttribute("author") @Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "/author/authorForm";
        }
        authorDao.merge(author);
        return "redirect:/author/form/all";
    }

    @GetMapping("/remove")
    public String prepareRemove(@RequestParam long id, Model model) {
        model.addAttribute("author", authorDao.findById(id));
        return "/author/remove";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam String confirmed, @RequestParam long id) {
        if ("yes".equals(confirmed)) {
            authorDao.remove(id);
        }
        return "redirect:/author/form/all";
    }

}
