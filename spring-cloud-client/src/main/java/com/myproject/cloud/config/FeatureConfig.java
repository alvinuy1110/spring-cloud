package com.myproject.cloud.config;

import com.myproject.cloud.domain.Features;
import com.myproject.cloud.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * This approach works but you have to put @RefreshScope at the config and the Bean that uses it
 */

@RefreshScope
@Configuration
public class FeatureConfig {

    @Autowired
    private Environment env;

    @RefreshScope
    @Bean
    public Features features() {

        Map<String, Object> map = getAllKnownProperties("feature.");
        Features features = new Features();
        features.setFeaturesMap(map);
        return features;
    }

    public Map<String, Object> getAllKnownProperties(String prefix) {
        Map<String, Object> rtn = new HashMap<>();
        if (env instanceof ConfigurableEnvironment) {
            for (PropertySource<?> propertySource : ((ConfigurableEnvironment) env).getPropertySources()) {
                if (propertySource instanceof EnumerablePropertySource) {
                    for (String key : ((EnumerablePropertySource) propertySource).getPropertyNames()) {

                        if (key.startsWith(prefix)) {
                            rtn.put(key, propertySource.getProperty(key));
                        }
                    }
                }
            }
        }


        return rtn;
    }

}
