package org.curenosm.springcloud.msvc.cursos.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidatorConfiguration {

    private final MessageSource messageSource;

    @Autowired
    public ValidatorConfiguration(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean validatorFactory = new LocalValidatorFactoryBean();
        validatorFactory.setValidationMessageSource(messageSource);
        return validatorFactory;
    }
}
