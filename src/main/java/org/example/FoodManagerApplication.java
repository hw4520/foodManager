package org.example;

import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.example.model.FoodEntity;
import org.example.service.FoodManagerService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoodManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodManagerApplication.class, args);
    }

    @Bean
    public ApplicationRunner foodManagerInitializer(FoodManagerService foodManagerService) {
        return args -> {
            foodManagerService.addFoodItem(new FoodEntity(1L, "Kimbab", "Lee Park"));
            foodManagerService.addFoodItem(new FoodEntity(2L, "Ramen", "Choi Cho"));
            foodManagerService.addFoodItem(new FoodEntity(3L, "Tteokbokki", "Kim Shin"));
        };
    }

    /*HTTPS 지원*/
    @Bean
    TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        var factory = new TomcatServletWebServerFactory();
        factory.addAdditionalTomcatConnectors(httpConnector());
        factory.addContextCustomizers(securityCustomizer());
        return factory;
    }
    private Connector httpConnector() {
        var connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }
    private TomcatContextCustomizer securityCustomizer() {
        return context -> {
            var securityConstraint = new SecurityConstraint();
            securityConstraint.setUserConstraint("CONFIDENTIAL");
            var collection = new SecurityCollection();
            collection.addPattern("/*");
            securityConstraint.addCollection(collection);
            context.addConstraint(securityConstraint);
        };
    }
    /*HTTPS 지원*/
}
