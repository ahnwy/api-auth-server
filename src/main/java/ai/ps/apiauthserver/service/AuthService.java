package ai.ps.apiauthserver.service;

import ai.ps.apiauthserver.model.dto.TokenMgmtDTO;
import ai.ps.apiauthserver.model.vo.ResUserVO;
import ai.ps.apiauthserver.model.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthService{
    // 토큰발급 여부확인
    public Optional<TokenMgmtDTO> checkTokenIssuance(UserVO userVO);

    // 토큰발급
    public String getToken(UserVO userVO);

    // 토큰정보 저장
    public int saveToken(TokenMgmtDTO tokenMgmtDTO);

    //UUID조회
    public String getServiceKey(int userId);

    public ResUserVO removeToken(UserVO userVO);

    public int createUserMetering(UserVO userVO);
}
