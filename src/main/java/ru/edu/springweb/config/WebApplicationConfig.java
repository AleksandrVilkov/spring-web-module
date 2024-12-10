package ru.edu.springweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "ru.edu.springweb")
public class WebApplicationConfig implements WebMvcConfigurer {

    private static final String RESOLVER_PREFIX = "/WEB-INF/views/";
    private static final String RESOLVER_SUFFIX = ".html";
    private static final String UTF_8 = "UTF-8";
    private static final int RENDER_ORDER = 1;


    @Bean
    public ClassLoaderTemplateResolver templateResolver() {
        var resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix(RESOLVER_PREFIX);
        resolver.setCacheable(false);
        resolver.setSuffix(RESOLVER_SUFFIX);
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding(UTF_8);
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        var engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        var resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding(UTF_8);
        resolver.setOrder(RENDER_ORDER);
        return resolver;
    }
}
