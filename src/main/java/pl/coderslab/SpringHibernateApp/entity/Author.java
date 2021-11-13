package pl.coderslab.SpringHibernateApp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.pl.PESEL;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;;

@Entity
@Table(name = "authors")
@Getter
@Setter
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank()
    private String firstName;

    @NotBlank
    private String lastName;

    @PESEL
    private String pesel;

    @Email
    @NotBlank
    private String email;

    public String getFullName() {
        return firstName + " " + lastName;
    }




}
