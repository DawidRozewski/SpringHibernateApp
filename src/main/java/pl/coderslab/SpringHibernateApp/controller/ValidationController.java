package pl.coderslab.SpringHibernateApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.SpringHibernateApp.entity.Book;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Controller
public class ValidationController {

    private final Validator validator;

    public ValidationController(Validator validator) {
        this.validator = validator;
    }

    @GetMapping("/validate")
    @ResponseBody
    public String validate() {
        Book book = new Book();
        book.setTitle("asd");
        book.setRating(15);
        book.setPages(0);

        Set<ConstraintViolation<Book>> validationResult = validator.validate(book);
        if (!validationResult.isEmpty()) {
            for(ConstraintViolation<Book> singleError : validationResult) {
                System.out.println("BŁĄĄĄD!!!!!!!!!!!!" + singleError.getPropertyPath() + " : " + singleError.getMessage());
            }
            return "Encja jest nie poprawna";
        } else {
            return "Encja jest poprawna";
        }

    }


}
