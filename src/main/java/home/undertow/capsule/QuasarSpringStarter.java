package home.undertow.capsule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.WebApplicationInitializer;


//import co.paralleluniverse.springframework.boot.autoconfigure.web.FiberSpringBootApplication;
//import co.paralleluniverse.springframework.web.servlet.config.annotation.FiberWebMvcConfigurationSupport;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;

/**
 * Created by alex on 9/5/2015.
 */
//@FiberSpringBootApplication
@ComponentScan("home.undertow.capsule")
@PropertySource("classpath:application.properties")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, WebMvcAutoConfiguration.class})
@Import({PersistenceJPAConfig.class, CacheConfiguration.class, RestConfiguration.class, /*FiberWebMvcConfigurationSupport.class, MongoConfiguration.class*/})
public class QuasarSpringStarter extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(QuasarSpringStarter.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }

    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory(){
        UndertowEmbeddedServletContainerFactory result = new UndertowEmbeddedServletContainerFactory();
        return result;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
