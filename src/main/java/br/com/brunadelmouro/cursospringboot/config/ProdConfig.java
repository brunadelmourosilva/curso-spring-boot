package br.com.brunadelmouro.cursospringboot.config;

import br.com.brunadelmouro.cursospringboot.services.DBService;
import br.com.brunadelmouro.cursospringboot.services.EmailService;
import br.com.brunadelmouro.cursospringboot.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("prod") //bean active
public class ProdConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}") //get value "create"
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if(!strategy.equals("create")){
            return false;
        }

        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
