package com.ns.cjcq.security.config;

import com.ns.cjcq.security.common.CJSpringSecurityProperty;
import com.ns.cjcq.security.common.properties.ImoocSecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(value = {CJSpringSecurityProperty.class, ImoocSecurityProperties.class})
public class CJSpringSecurityConfig{


}
