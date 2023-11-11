package dev.bayun.api.gateway.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

/**
 * @author Максим Яськов
 */
@Configuration
@RequiredArgsConstructor
public class ServerSecurityConfiguration {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(configurer -> configurer
                .pathMatchers("/login/**", "/oauth2/**").permitAll()
                .anyExchange().authenticated());
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        http.oauth2ResourceServer(resourceServer -> resourceServer.jwt(Customizer.withDefaults()));
        http.securityContextRepository(NoOpServerSecurityContextRepository.getInstance());
        return http.build();
    }

}
