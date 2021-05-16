package com.ns.cjcq.security.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "cjcq.security")
public class CJSpringSecurityProperty {
    private Long cjRootResourceId;

}
