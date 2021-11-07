package pl.coderslab.SpringHibernateApp.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateApp.entity.Publisher;
import pl.coderslab.SpringHibernateApp.repository.PublisherRepository;
import pl.coderslab.SpringHibernateApp.search.PublisherSearchMode;

import javax.validation.Valid;

@Controller
@RequestMapping("/publisher/form")
public class PublisherFormController {

    private final PublisherRepository publisherRepository;

    public PublisherFormController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/all")
    public String showAll(Model model,
                          @RequestParam(value = "field", required = false) PublisherSearchMode field,
                          @RequestParam(value = "query", required = false) String query) {
        if (field != null && StringUtils.isNotEmpty(query)) {
            switch (field) {
                case NIP:
                    model.addAttribute("publishers", publisherRepository.findByNip(query));
                    break;
                case REGON:
                    model.addAttribute("publishers", publisherRepository.findByRegon(query));
                    break;
            }
            model.addAttribute("selectedField", field);
        } else {
            model.addAttribute("publishers", publisherRepository.findAll());
        }
        model.addAttribute("query", query);
        model.addAttribute("searchMode", PublisherSearchMode.values());
        return "/publisher/publisherListing";
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
        publisherRepository.save(publisher);
        return "redirect:/publisher/form/all";
    }

    @GetMapping("/edit")
    public String prepareToEdit(@RequestParam long id, Model model) {
        model.addAttribute("publisher", publisherRepository.findById(id));
        return "publisher/publisherForm";
    }

    @PostMapping("/edit")
    public String merge(@ModelAttribute("publisher") @Valid Publisher publisher, BindingResult result) {
        if(result.hasErrors()) {
            return "/publisher/publisherForm";
        }
        publisherRepository.save(publisher);
        return "redirect:/publisher/form/all";
    }

    @GetMapping("/remove")
    public String prepareToRemove(@RequestParam long id, Model model) {
        model.addAttribute("publisher", publisherRepository.findById(id));
        return "publisher/remove";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam String confirmed, @RequestParam long id) {
        if ("yes".equals(confirmed)) {
            publisherRepository.deleteById(id);
        }
        return "redirect:/publisher/form/all";
    }


}
