package ai.ps.apiauthserver.controller;

import ai.ps.apiauthserver.model.dto.TokenMgmtDTO;
import ai.ps.apiauthserver.model.vo.JwtTokenVO;
import ai.ps.apiauthserver.model.vo.UserVO;
import ai.ps.apiauthserver.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<JwtTokenVO> getToken(@RequestBody UserVO userVO){
        logger.info("userVO >>> " + userVO);
        // 이미 토큰이 발급되어 있는지 확인
        Optional<TokenMgmtDTO> tokenInfo = authService.checkTokenIssuance(userVO);
        logger.info(String.valueOf(tokenInfo.isPresent()));
        // 발급되어 있을 경우 해당 UUID 리턴
        if(tokenInfo.isPresent()){
            TokenMgmtDTO tokenMgmtDTO = tokenInfo.get();
            return ResponseEntity.ok().body(
                    JwtTokenVO.ofToken(tokenMgmtDTO.getSvcKey()
                            , tokenMgmtDTO.getToken()
                    ));
        } else {
            // 유저아이디에 대한 토큰 발급여부 확인
            String use_uuid = authService.getServiceKey(userVO.getUserId());
            // 유저아이디에 대한 토큰이 발급되어 있는 경우 해당 UUID사용
            // 발급되어 있지 않은 경우 UUID생성
            String uuid = Objects.requireNonNullElseGet(use_uuid, () -> UUID.randomUUID().toString().replace("-", ""));
            String token = authService.getToken(userVO);
            TokenMgmtDTO tokenMgmtDTO = TokenMgmtDTO.ofBuild(userVO, token, uuid);
            // 토큰정보 저장
            authService.saveToken(tokenMgmtDTO);
            // 미터링 초기값 생성
            authService.createUserMetering(userVO);
            return ResponseEntity.ok().body(JwtTokenVO.ofToken(uuid, token));
        }
    }

    // 토큰 삭제
    @RequestMapping(value = {"/oauth/remove/token"}, method = {RequestMethod.DELETE})
    public ResponseEntity<?> removeToken(@RequestBody UserVO userVO){
        return ResponseEntity.ok().body(authService.removeToken(userVO));
    }
}
