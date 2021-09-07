package ai.ps.apiauthserver.controller;

import ai.ps.apiauthserver.model.dto.AuthUserDTO;
import ai.ps.apiauthserver.model.vo.AuthVO;
import ai.ps.apiauthserver.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    @Autowired
    AuthService authService;

    @RequestMapping(value = {"/getUser"}, method = {RequestMethod.GET, RequestMethod.POST})
    public AuthUserDTO getUser(AuthVO authVO) {
        authService.getAccount(authVO.getUsername());
        return new AuthUserDTO();
    }

    @RequestMapping(value = {"/api"}, method = {RequestMethod.GET})
    public String get() {
        return "hi";
    }
}
