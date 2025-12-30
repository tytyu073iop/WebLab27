package by.bsu.biryuk.Lab7;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import java.util.Locale;

@Slf4j
@Configuration
public class LocaleConfig {

    @Bean
    public SessionLocaleResolver localeResolver() {
        log.info("Configuring locale resolver");
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.forLanguageTag("ru")); // язык по умолчанию
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        log.info("Configuring locale change interceptor");
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang"); 
        return lci;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(LocaleChangeInterceptor lci) {
        log.info("Configuring web mvc configurer");
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(lci);
            }
        };
    }
}

