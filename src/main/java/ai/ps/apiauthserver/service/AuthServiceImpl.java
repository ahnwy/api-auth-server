package ai.ps.apiauthserver.service;

import ai.ps.apiauthserver.model.dto.AuthUserDTO;
import ai.ps.apiauthserver.mapper.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    AuthMapper authMapper;

    // 유저 정보 조회
    @Override
    public AuthUserDTO getAccount(String username){
        return authMapper.getAccount(username);
    }

    // 유저 권한 조회
    @Override
    public Collection<GrantedAuthority> getAuthorities(int userId){
        List<String> string_authorities = authMapper.getAuthorities(userId);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String authority : string_authorities) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        return authorities;
    }
}
