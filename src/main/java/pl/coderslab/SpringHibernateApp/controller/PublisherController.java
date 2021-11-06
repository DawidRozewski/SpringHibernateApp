package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.SpringHibernateApp.dao.PublisherDao;
import pl.coderslab.SpringHibernateApp.entity.Publisher;
import pl.coderslab.SpringHibernateApp.repository.PublisherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherDao publisherDao;
    private final PublisherRepository publisherRepository;

    public PublisherController(PublisherDao publisherDao, PublisherRepository publisherRepository) {
        this.publisherDao = publisherDao;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/findByNip/{nip}")
    @ResponseBody
    public String findByNip(@PathVariable String nip) {
        return publisherRepository.findByNip(nip).toString();
    }

    @GetMapping("/findByRegon/{regon}")
    @ResponseBody
    public String findByRegon(@PathVariable String regon) {
        return publisherRepository.findByRegon(regon).toString();
    }

    @GetMapping("/save")
    @ResponseBody
    public String persist() {
        Publisher publisher = new Publisher();
        publisher.setName("PWN");
        publisherDao.persist(publisher);
        return publisher.toString();
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public String findById(@PathVariable long id) {
        Publisher publisher = publisherDao.findById(id);
        return publisher.toString();
    }

    @GetMapping("/update/{id}/{name}")
    @ResponseBody
    public String merge(@PathVariable long id, @PathVariable String name) {
        Publisher publisher = publisherDao.findById(id);
        publisher.setName(name);
        publisherDao.merge(publisher);
        return publisher.toString();
    }

    @GetMapping("/remove/{id}")
    @ResponseBody
    public String remove(@PathVariable long id) {
        publisherDao.remove(id);
        return "Usunieto wydawce";
    }

    @GetMapping("/all")
    @ResponseBody
    public String findAll()  {
        List<Publisher> all = publisherDao.findAll();
        return all.stream()
                .map(publisher -> publisher.getId() + ": " + publisher.getName())
                .collect(Collectors.joining("<br />"));
    }

}
