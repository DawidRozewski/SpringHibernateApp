package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.SpringHibernateApp.Student;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @ModelAttribute("countries")
    public List<String> countries() {
        return Arrays.asList("Poland", "Germany", "France", "Russia", "Denmark");
    }
    @ModelAttribute("hobbies")
    public List<String> hobbies() {
        return Arrays.asList("boks", "kickboxing", "muaythai", "gady");
    }

    @ModelAttribute("programmingSkills")
    public List<String> skills() {
        return Arrays.asList("Java", "Cpp", "Ruby", "Python", "PHP");
    }

    @GetMapping("/form")
    public String getForm(Model model){
        model.addAttribute("student", new Student());
        return "/student/form";
    }
    @PostMapping("/form")
    @ResponseBody
    public String savePerson(@ModelAttribute("student") Student student) {
        return student.toString();
    }




}
