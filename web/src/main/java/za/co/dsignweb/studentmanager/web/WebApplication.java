package za.co.dsignweb.studentmanager.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
        "za.co.dsignweb.studentmanager.db.repository",
})
@EntityScan(basePackages = {
        "za.co.dsignweb.studentmanager.db.entity",
})
@ComponentScan(basePackages = {
        "za.co.dsignweb.studentmanager"
})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
