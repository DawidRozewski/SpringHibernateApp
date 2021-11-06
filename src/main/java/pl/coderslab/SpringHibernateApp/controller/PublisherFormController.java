package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateApp.dao.PublisherDao;
import pl.coderslab.SpringHibernateApp.entity.Publisher;

import javax.validation.Valid;

@Controller
@RequestMapping("/publisher/form")
public class PublisherFormController {

    private final PublisherDao publisherDao;

    public PublisherFormController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("publishers", publisherDao.findAll());
        return "publisher/publisherListing";
    }

    @GetMapping("/add")
    public String prepareToAdd(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publisher/publisherForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("publisher") @Valid Publisher publisher, BindingResult result) {
        if(result.hasErrors()) {
            return "/publish/publisherForm";
        }
        publisherDao.persist(publisher);
        return "redirect:/publisher/form/all";
    }

    @GetMapping("/edit")
    public String prepareToEdit(@RequestParam long id, Model model) {
        model.addAttribute("publisher", publisherDao.findById(id));
        return "publisher/publisherForm";
    }

    @PostMapping("/edit")
    public String merge(@ModelAttribute("publisher") @Valid Publisher publisher, BindingResult result) {
        if(result.hasErrors()) {
            return "/publisher/publisherForm";
        }
        publisherDao.merge(publisher);
        return "redirect:/publisher/form/all";
    }

    @GetMapping("/remove")
    public String prepareToRemove(@RequestParam long id, Model model) {
        model.addAttribute("publisher", publisherDao.findById(id));
        return "publisher/remove";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam String confirmed, @RequestParam long id) {
        if ("yes".equals(confirmed)) {
            publisherDao.remove(id);
        }
        return "redirect:/publisher/form/all";
    }


}
