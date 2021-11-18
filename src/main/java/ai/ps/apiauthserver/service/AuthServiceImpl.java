package ai.ps.apiauthserver.service;

import ai.ps.apiauthserver.mapper.AuthMapper;
import ai.ps.apiauthserver.model.dto.TokenManagementDTO;
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
    @Override
    public String getToken(UserVO userVO){
        return jwtToken(userVO);
    }
    // 토큰 생성
    public String jwtToken(UserVO userVO){
        return jwtTokenProvider.createToken(userVO.getUserId(), Collections.singletonList("USER"));
    }

    // 토큰발급 확인
    @Override
    public Optional<TokenManagementDTO> getTokenYn(UserVO userVO){
        return authMapper.selectTokenManagement(userVO);
    }

    // 토큰정보 저장
    public int saveToken(TokenManagementDTO tokenManagementDTO){
        return authMapper.saveToken(tokenManagementDTO);
    }

    // UUID 조회
    @Override
    public String getUuid(int userId){
        return authMapper.getUuid(userId);
    }
}
