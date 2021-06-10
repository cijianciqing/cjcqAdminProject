package com.ns.cjcq.security.authorize.config;

import com.ns.cjcq.security.common.properties.ImoocSecurityProperties;
import com.ns.cjcq.security.validator.interfaceAndImpiment.ValidatorGenerator;
import com.ns.cjcq.security.validator.interfaceAndImpiment.ValidatorGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CJAuthorityConfig {

    @Autowired
    ImoocSecurityProperties securityProperties;

}
