package ap.ps.apiauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${spring.security.jwt.client_id}")
    private String client_id;
    @Value("${spring.security.jwt.client_secret}")
    private String client_secret;
    @Value("${spring.security.jwt.grant_type}")
    private String grant_type;
    @Value("${spring.security.jwt.refresh_token}")
    private String refresh_token;
    @Value("${spring.security.jwt.access_token_validity_seconds}")
    private int access_token_validity_seconds;
    @Value("${spring.security.jwt.fresh_token_validity_seconds}")
    private int fresh_token_validity_seconds;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

        configurer
                .inMemory()
                .withClient(client_id)
                .secret(passwordEncoder.encode(client_secret))
                .authorizedGrantTypes(grant_type,refresh_token)
                .scopes("read")
                .accessTokenValiditySeconds(access_token_validity_seconds)
                .refreshTokenValiditySeconds(fresh_token_validity_seconds);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        return new JwtAccessTokenConverter();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .accessTokenConverter(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager);
    }
}
