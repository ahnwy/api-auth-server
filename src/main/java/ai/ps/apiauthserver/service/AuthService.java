package ai.ps.apiauthserver.service;

import ai.ps.apiauthserver.model.dto.AuthUserDTO;
import ai.ps.apiauthserver.model.dto.TokenManagementDTO;
import ai.ps.apiauthserver.model.vo.JwtTokenVO;
import ai.ps.apiauthserver.model.vo.UserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface AuthService{
    // 토큰발급 여부확인
    public Optional<TokenManagementDTO> getTokenYn(UserVO userVO);

    // 토큰발급
    public String getToken(UserVO userVO);

    // 토큰정보 저장
    public int saveToken(TokenManagementDTO tokenManagementDTO);

    //UUID조회
    public String getUuid(int userId);
}
