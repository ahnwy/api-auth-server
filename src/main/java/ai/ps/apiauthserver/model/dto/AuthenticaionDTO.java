package ai.ps.apiauthserver.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthenticaionDTO extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1L;

    String username;
    AuthUserDTO user;

    public AuthenticaionDTO(String username, String password, List<GrantedAuthority> grantedAuthorityList, AuthUserDTO user) {
        super(username, password, grantedAuthorityList);
        this.user = user;
        this.username = username;
    }
}
