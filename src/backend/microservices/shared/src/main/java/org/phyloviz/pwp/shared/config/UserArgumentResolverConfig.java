package org.phyloviz.pwp.shared.config;

import java.util.List;
import lombok.AllArgsConstructor;
import org.phyloviz.pwp.shared.http.pipeline.UserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class UserArgumentResolverConfig implements WebMvcConfigurer {

    private UserArgumentResolver userArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }
}
