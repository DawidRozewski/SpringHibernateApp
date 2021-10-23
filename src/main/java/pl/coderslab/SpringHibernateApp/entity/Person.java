package pl.coderslab.SpringHibernateApp.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "people")
@Getter
@Setter
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String email;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private PersonDetails personDetails;

}
