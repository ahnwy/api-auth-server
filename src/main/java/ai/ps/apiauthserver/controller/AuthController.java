package ai.ps.apiauthserver.controller;

import ai.ps.apiauthserver.model.dto.TokenManagementDTO;
import ai.ps.apiauthserver.model.vo.JwtTokenVO;
import ai.ps.apiauthserver.model.vo.UserVO;
import ai.ps.apiauthserver.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    // token발급요청
    @RequestMapping(value = {"/oauth/token"}, method = {RequestMethod.POST})
    public ResponseEntity<JwtTokenVO> getToken(UserVO userVO){
        // 이미 토큰이 발급되어 있는지 확인
        Optional<TokenManagementDTO> tokenInfo = authService.getTokenYn(userVO);
        // 발급되어 있을 경우 해당 UUID 리턴
        if(tokenInfo.isPresent()){
            TokenManagementDTO tokenManagementDTO = tokenInfo.get();
            return ResponseEntity.ok().body(
                    JwtTokenVO.ofToken(tokenManagementDTO.getUuid()
                            ,tokenManagementDTO.getToken()
                    ));
        } else {
            // 유저아이디에 대한 토큰 발급여부 확인
            String use_uuid = authService.getUuid(userVO.getUserId());
            // 유저아이디에 대한 토큰이 발급되어 있는 경우 해당 UUID사용
            // 발급되어 있지 않은 경우 UUID생성
            String uuid = Objects.requireNonNullElseGet(use_uuid, () -> UUID.randomUUID().toString().replace("-", ""));
            String token = authService.getToken(userVO);
            TokenManagementDTO tokenManagementDTO = TokenManagementDTO.ofSave(userVO, token, uuid);
            // 토큰정보 저장
            authService.saveToken(tokenManagementDTO);
            return ResponseEntity.ok().body(JwtTokenVO.ofToken(uuid, token));
        }
    }
}
