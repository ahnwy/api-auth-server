package ai.ps.apiauthserver.service;

import ai.ps.apiauthserver.mapper.AuthMapper;
import ai.ps.apiauthserver.model.dto.TokenMgmtDTO;
import ai.ps.apiauthserver.model.vo.ResUserVO;
import ai.ps.apiauthserver.model.vo.UserVO;
import ai.ps.apiauthserver.provider.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthMapper authMapper;

    //토큰발급
    public String getToken(UserVO userVO){
        return createJwtToken(userVO);
    }

    // 토큰 생성
    public String createJwtToken(UserVO userVO){ return jwtTokenProvider.createJwtToken(userVO.getUserId(), Collections.singletonList("USER")); }

    // 토큰발급 확인
    public Optional<TokenMgmtDTO> checkTokenIssuance(UserVO userVO){ return authMapper.selectTokenMgmt(userVO); }

    // 토큰정보 저장
    public int saveToken(TokenMgmtDTO tokenMgmtDTO){ return authMapper.saveToken(tokenMgmtDTO); }
    // 미터링 테이블에 유저 초기값으로 추가
    public int createUserMetering(UserVO userVO){
        return authMapper.createUserMetering(userVO);
    }

    // UUID 조회
    public String getServiceKey(int userId){
        return authMapper.getServiceKey(userId);
    }

    // 토큰정보 삭제
    public ResUserVO removeToken(UserVO userVO) {
        ResUserVO resUserVO = new ResUserVO();
        int res = authMapper.removeToken(userVO);
        if(res == 1){
            resUserVO.setMessage("Remove Successful");
        } else {
            resUserVO.setMessage("Remove Failed");
        }
        return resUserVO;
    }
}
