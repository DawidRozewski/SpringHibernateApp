package pl.coderslab.SpringHibernateApp;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "pl.coderslab")
@EnableTransactionManagement
public class AppConfig {

}
