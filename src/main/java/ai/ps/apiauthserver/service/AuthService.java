package ai.ps.apiauthserver.service;

import ai.ps.apiauthserver.model.dto.AuthUserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface AuthService{

    // 유저 정보 조회
    public AuthUserDTO getAccount(String username);
    // 유저 권한 조회
    public Collection<GrantedAuthority> getAuthorities(int userId);
}
