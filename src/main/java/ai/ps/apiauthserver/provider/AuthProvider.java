package ai.ps.apiauthserver.provider;

import ai.ps.apiauthserver.model.dto.AuthUserDTO;
import ai.ps.apiauthserver.model.dto.AuthenticaionDTO;
import ai.ps.apiauthserver.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("authProvider")
public class AuthProvider implements AuthenticationProvider  {

    @Autowired
    AuthService authService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        AuthUserDTO authUserDTO = authService.getAccount(username);
        System.out.println(authUserDTO);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList(authService.getAuthorities(authUserDTO.getUserId()));
        return new AuthenticaionDTO(username, password, grantedAuthorityList, authUserDTO);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
