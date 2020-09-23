package org.techfrog.gateway;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties
public class UriConfiguration {

    private String fooServiceUrl;
    private String barServiceUrl;
}
