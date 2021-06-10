package com.ns.cjcq.security.validator.config;

import com.ns.cjcq.security.common.properties.ImoocSecurityProperties;

import com.ns.cjcq.security.validator.interfaceAndImpiment.ValidatorGenerator;
import com.ns.cjcq.security.validator.interfaceAndImpiment.ValidatorGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {

    @Autowired
    ImoocSecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name="validatorGenerator")
    public ValidatorGenerator validatorGenerator(){
        ValidatorGeneratorImpl validatorGenerator =  new ValidatorGeneratorImpl();
        validatorGenerator.setSecurityProperties(securityProperties);
        return validatorGenerator;
    }
}
